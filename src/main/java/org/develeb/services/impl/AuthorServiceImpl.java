package org.develeb.services.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements org.develeb.services.AuthorService {

    private org.develeb.repositories.AuthorRepository authorRepository;

    public AuthorServiceImpl(org.develeb.repositories.AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public org.develeb.domain.entities.AuthorEntity save(org.develeb.domain.entities.AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<org.develeb.domain.entities.AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository
                        .findAll()
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<org.develeb.domain.entities.AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public org.develeb.domain.entities.AuthorEntity partialUpdate(Long id, org.develeb.domain.entities.AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not exist"));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
