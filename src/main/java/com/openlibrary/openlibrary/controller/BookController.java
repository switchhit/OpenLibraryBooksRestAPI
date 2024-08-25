package com.openlibrary.openlibrary.controller;

import com.openlibrary.openlibrary.model.Book;
import com.openlibrary.openlibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/searchbook")
    public Book searchBookByName(@RequestParam String name){
        return service.getBookByName(name);
    }

    @DeleteMapping("/deletebook")
    public void deleteBookByName(@RequestParam String name){
        service.deleteBookByName(name);
    }
}
