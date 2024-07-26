package org.develeb.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements org.develeb.mappers.Mapper<org.develeb.domain.entities.AuthorEntity, org.develeb.domain.dto.AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public org.develeb.domain.dto.AuthorDto mapTo(org.develeb.domain.entities.AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, org.develeb.domain.dto.AuthorDto.class);
    }

    @Override
    public org.develeb.domain.entities.AuthorEntity mapFrom(org.develeb.domain.dto.AuthorDto authorDto) {
        return modelMapper.map(authorDto, org.develeb.domain.entities.AuthorEntity.class);
    }
}
