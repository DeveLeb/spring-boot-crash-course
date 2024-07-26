package org.develeb.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements org.develeb.mappers.Mapper<org.develeb.domain.entities.BookEntity, org.develeb.domain.dto.BookDto> {

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public org.develeb.domain.dto.BookDto mapTo(org.develeb.domain.entities.BookEntity bookEntity) {
        return modelMapper.map(bookEntity, org.develeb.domain.dto.BookDto.class);
    }

    @Override
    public org.develeb.domain.entities.BookEntity mapFrom(org.develeb.domain.dto.BookDto bookDto) {
        return modelMapper.map(bookDto, org.develeb.domain.entities.BookEntity.class);
    }
}
