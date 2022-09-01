package com.wspxxxx.bookstoremanager.controller;

import com.wspxxxx.bookstoremanager.dto.MessageResponseDTO;
import com.wspxxxx.bookstoremanager.entity.Book;
import com.wspxxxx.bookstoremanager.repository.BookRepository;
import com.wspxxxx.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class Controller {


    private BookService bookService;

    @Autowired
    public Controller(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public MessageResponseDTO create(@RequestBody Book book){
        return bookService.create(book);
    }
}
