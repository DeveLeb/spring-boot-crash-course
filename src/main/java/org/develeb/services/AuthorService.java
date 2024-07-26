package org.develeb.services;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    org.develeb.domain.entities.AuthorEntity save(org.develeb.domain.entities.AuthorEntity authorEntity);

    List<org.develeb.domain.entities.AuthorEntity> findAll();

    Optional<org.develeb.domain.entities.AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    org.develeb.domain.entities.AuthorEntity partialUpdate(Long id, org.develeb.domain.entities.AuthorEntity authorEntity);

    void delete(Long id);
}
