package org.develeb.controllers;

import org.develeb.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private BookService bookService;
    private org.develeb.mappers.Mapper<org.develeb.domain.entities.BookEntity, org.develeb.domain.dto.BookDto> bookMapper;

    public BookController(org.develeb.mappers.Mapper<org.develeb.domain.entities.BookEntity, org.develeb.domain.dto.BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<org.develeb.domain.dto.BookDto> createUpdateBook(@PathVariable String isbn, @RequestBody org.develeb.domain.dto.BookDto bookDto) {
        org.develeb.domain.entities.BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);
        org.develeb.domain.entities.BookEntity savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
        org.develeb.domain.dto.BookDto savedUpdatedBookDto = bookMapper.mapTo(savedBookEntity);

        if(bookExists){
            return new ResponseEntity(savedUpdatedBookDto, HttpStatus.OK);
        } else {
            return new ResponseEntity(savedUpdatedBookDto, HttpStatus.CREATED);
        }
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<org.develeb.domain.dto.BookDto> partialUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody org.develeb.domain.dto.BookDto bookDto
    ){
        if(!bookService.isExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        org.develeb.domain.entities.BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        org.develeb.domain.entities.BookEntity updatedBookEntity = bookService.partialUpdate(isbn, bookEntity);
        return new ResponseEntity<>(
                bookMapper.mapTo(updatedBookEntity),
                HttpStatus.OK);

    }

    @GetMapping(path = "/books")
    public List<org.develeb.domain.dto.BookDto> listBooks() {
        List<org.develeb.domain.entities.BookEntity> books = bookService.findAll();
        return books.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<org.develeb.domain.dto.BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<org.develeb.domain.entities.BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            org.develeb.domain.dto.BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
