package com.openlibrary.openlibrary.repository;

import com.openlibrary.openlibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String name);
    void deleteByTitle(String name);
}
