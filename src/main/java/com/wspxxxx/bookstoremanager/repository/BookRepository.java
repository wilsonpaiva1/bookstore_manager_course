package com.wspxxxx.bookstoremanager.repository;

import com.wspxxxx.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
