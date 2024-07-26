package org.develeb.controllers;

import org.develeb.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;

    private org.develeb.mappers.Mapper<org.develeb.domain.entities.AuthorEntity, org.develeb.domain.dto.AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, org.develeb.mappers.Mapper<org.develeb.domain.entities.AuthorEntity, org.develeb.domain.dto.AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<org.develeb.domain.dto.AuthorDto> createAuthor(@RequestBody org.develeb.domain.dto.AuthorDto author) {
        org.develeb.domain.entities.AuthorEntity authorEntity = authorMapper.mapFrom(author);
        org.develeb.domain.entities.AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<org.develeb.domain.dto.AuthorDto> listAuthors() {
        List<org.develeb.domain.entities.AuthorEntity> authors = authorService.findAll();
        return authors.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<org.develeb.domain.dto.AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<org.develeb.domain.entities.AuthorEntity> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(authorEntity -> {
            org.develeb.domain.dto.AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<org.develeb.domain.dto.AuthorDto> fullUpdateAuthor(
            @PathVariable("id") Long id,
            @RequestBody org.develeb.domain.dto.AuthorDto authorDto) {

        if(!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);
        org.develeb.domain.entities.AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        org.develeb.domain.entities.AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(
                authorMapper.mapTo(savedAuthorEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<org.develeb.domain.dto.AuthorDto> partialUpdate(
            @PathVariable("id") Long id,
            @RequestBody org.develeb.domain.dto.AuthorDto authorDto
    ) {
        if(!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        org.develeb.domain.entities.AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        org.develeb.domain.entities.AuthorEntity updatedAuthor = authorService.partialUpdate(id, authorEntity);
        return new ResponseEntity<>(
                authorMapper.mapTo(updatedAuthor),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
