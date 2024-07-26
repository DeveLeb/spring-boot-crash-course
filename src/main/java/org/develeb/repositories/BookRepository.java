package org.develeb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<org.develeb.domain.entities.BookEntity, String>,
        PagingAndSortingRepository<org.develeb.domain.entities.BookEntity, String> {
}
