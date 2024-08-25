package com.openlibrary.openlibrary.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {
    private List<OLBook> docs;


    public BookResponse() {
    }

    public List<OLBook> getDocs() {
        return docs;
    }

    public void setBookList(List<OLBook> bookList) {
        this.docs = bookList;
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class OLBook {
    private String key;
    private String title;
    private List<String> author_name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    public OLBook() {
    }
}