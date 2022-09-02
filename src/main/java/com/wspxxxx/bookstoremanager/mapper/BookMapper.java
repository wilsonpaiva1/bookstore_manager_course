package com.wspxxxx.bookstoremanager.mapper;

import com.wspxxxx.bookstoremanager.dto.BookDTO;
import com.wspxxxx.bookstoremanager.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book book);

    Book toModel(BookDTO bookDTO);



}
