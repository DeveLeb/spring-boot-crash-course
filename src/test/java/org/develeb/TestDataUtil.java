package org.develeb;

public final class TestDataUtil {
    private TestDataUtil(){
    }

    public static org.develeb.domain.entities.AuthorEntity createTestAuthorEntityA() {
        return org.develeb.domain.entities.AuthorEntity.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static org.develeb.domain.dto.AuthorDto createTestAuthorDtoA() {
        return org.develeb.domain.dto.AuthorDto.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static org.develeb.domain.entities.AuthorEntity createTestAuthorB() {
        return org.develeb.domain.entities.AuthorEntity.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
    }

    public static org.develeb.domain.entities.AuthorEntity createTestAuthorC() {
        return org.develeb.domain.entities.AuthorEntity.builder()
                .id(3L)
                .name("Jesse A Casey")
                .age(24)
                .build();
    }

    public static org.develeb.domain.entities.BookEntity createTestBookEntityA(final org.develeb.domain.entities.AuthorEntity authorEntity) {
        return org.develeb.domain.entities.BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorEntity(authorEntity)
                .build();
    }

    public static org.develeb.domain.dto.BookDto createTestBookDtoA(final org.develeb.domain.dto.AuthorDto authorDto) {
        return org.develeb.domain.dto.BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(authorDto)
                .build();
    }

    public static org.develeb.domain.entities.BookEntity createTestBookB(final org.develeb.domain.entities.AuthorEntity authorEntity) {
        return org.develeb.domain.entities.BookEntity.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .authorEntity(authorEntity)
                .build();
    }

    public static org.develeb.domain.entities.BookEntity createTestBookC(final org.develeb.domain.entities.AuthorEntity authorEntity) {
        return org.develeb.domain.entities.BookEntity.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .authorEntity(authorEntity)
                .build();
    }
}
