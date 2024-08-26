package com.openlibrary.openlibrary.service;

import com.openlibrary.openlibrary.exception.BookNotFoundException;
import com.openlibrary.openlibrary.model.Book;
import com.openlibrary.openlibrary.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BookService {

    RestTemplate restTemplate;
    BookRepository repository;

    public BookService(RestTemplate restTemplate, BookRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }


    public Book getBookByName(String name) {
        Optional<Book> bookFromDb = repository.findByTitle(name.toLowerCase());
        if(bookFromDb.isPresent()){
            log.info("fetching the title {} from the database", name);
            return bookFromDb.get();
        }

        log.info("Title {} is not present in the database, trying to hit the API....", name);
        String url = "https://openlibrary.org/search.json?q="+name;
        BookResponse response = restTemplate.getForObject(url, BookResponse.class);
        if(response==null || response.getDocs().isEmpty()){
            log.error("Book not found");
            throw new BookNotFoundException("book not found");
        }

        OLBook olBook = response.getDocs().get(0);
        Book book = new Book();
        book.setTitle(olBook.getTitle().toLowerCase());
        book.setAuthor(olBook.getAuthor_name());
        book.setOpenId(olBook.getKey());
        repository.save(book);
        return book;

    }

    public void deleteBookByName(String name) {
        Optional<Book> bookFromDb = repository.findByTitle(name);
        if(bookFromDb.isPresent()) {
            log.info("found the book {} in the database... deleting it", name);
            repository.deleteByTitle(name);
        }
        else {
            log.info("{} book was not found in the database", name);
        }
    }
}
