package org.develeb.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<org.develeb.domain.entities.AuthorEntity, Long> {

    Iterable<org.develeb.domain.entities.AuthorEntity> ageLessThan(int age);

    @Query("SELECT a from AuthorEntity a where a.age > ?1")
    Iterable<org.develeb.domain.entities.AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
