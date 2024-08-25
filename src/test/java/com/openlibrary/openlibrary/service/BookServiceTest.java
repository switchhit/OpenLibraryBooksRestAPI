package com.openlibrary.openlibrary.service;

import com.openlibrary.openlibrary.controller.BookController;
import com.openlibrary.openlibrary.model.Book;
import com.openlibrary.openlibrary.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookPresentInDB(){
        String title = "a song of ice and fire";
        Book book = new Book();
        book.setTitle(title);
        when(bookRepository.findByTitle(title)).thenReturn(Optional.of(book));
        Book resultBook = bookService.getBookByName(title);
        assertNotNull(resultBook);
        assertEquals(book.getTitle(), resultBook.getTitle());
        verify(bookRepository, times(1)).findByTitle(title);
        verifyNoInteractions(restTemplate);
    }

    @Test
    void testBookNotPresentInDB(){
        String title = "a song of ice and fire";
        BookResponse response = new BookResponse();
        OLBook olBook = new OLBook();
        olBook.setTitle(title);
        olBook.setKey("/works/OL17358643W");
        olBook.setAuthor_name(List.of("George R. R. Martin"));
        response.setBookList(List.of(olBook));
        when(bookRepository.findByTitle(title)).thenReturn(Optional.empty());
        when(restTemplate.getForObject(anyString(), eq(BookResponse.class))).thenReturn(response);
        Book resultBook = bookService.getBookByName(title);
        assertNotNull(resultBook);
        assertEquals(title, resultBook.getTitle());
        verify(bookRepository, times(1)).findByTitle(title);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(BookResponse.class));

    }

    @Test
    void testDeleteByTitlePresentInDb(){
        String title = "a song of ice and fire";
        Book book = new Book();
        book.setTitle(title);
        when(bookRepository.findByTitle(title)).thenReturn(Optional.of(book));
        bookService.deleteBookByName(title);
        verify(bookRepository, times(1)).findByTitle(title);
        verify(bookRepository, times(1)).deleteByTitle(title);
    }

    @Test
    void testDeleteByTitleNotPresentInDb(){
        String title = "a song of ice and fire";
        Book book = new Book();
        book.setTitle(title);
        when(bookRepository.findByTitle(title)).thenReturn(Optional.empty());
        bookService.deleteBookByName(title);
        verify(bookRepository, times(1)).findByTitle(title);
        verify(bookRepository, times(0)).deleteByTitle(title);
    }
}
