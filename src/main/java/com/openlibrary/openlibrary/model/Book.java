package com.openlibrary.openlibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private List<String> Author;

    private String OpenId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor() {
        return Author;
    }

    public void setAuthor(List<String> author) {
        Author = author;
    }

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String openId) {
        OpenId = openId;
    }

    public Book(String title, List<String> author, String openId) {
        this.title = title;
        Author = author;
        OpenId = openId;
    }

    public Book() {
    }
}
