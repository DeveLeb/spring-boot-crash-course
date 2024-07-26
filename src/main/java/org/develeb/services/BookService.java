package org.develeb.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    org.develeb.domain.entities.BookEntity createUpdateBook(String isbn, org.develeb.domain.entities.BookEntity book);

    List<org.develeb.domain.entities.BookEntity> findAll();

    Page<org.develeb.domain.entities.BookEntity> findAll(Pageable pageable);

    Optional<org.develeb.domain.entities.BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    org.develeb.domain.entities.BookEntity partialUpdate(String isbn, org.develeb.domain.entities.BookEntity bookEntity);

    void delete(String isbn);

}
