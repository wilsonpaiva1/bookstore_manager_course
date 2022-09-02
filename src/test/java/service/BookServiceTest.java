package service;

import com.wspxxxx.bookstoremanager.dto.BookDTO;
import com.wspxxxx.bookstoremanager.entity.Book;
import com.wspxxxx.bookstoremanager.exception.BookNotFoundException;
import com.wspxxxx.bookstoremanager.repository.BookRepository;
import com.wspxxxx.bookstoremanager.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.BookUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static utils.BookUtils.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFoundBook = createFakeBook();
        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));
        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        assertEquals(expectedFoundBook.getName(),bookDTO.getName());
        assertEquals(expectedFoundBook.getIsbn(),bookDTO.getIsbn());
        assertEquals(expectedFoundBook.getPublisherName(),bookDTO.getPublisherName());

    }

    @Test
    void whenGivenUnexixtingIdThenNotFindThrowAnException() {
        var invalidId = 10l;
        when(bookRepository.findById(invalidId))
                .thenReturn(Optional.ofNullable(any(Book.class)));
        assertThrows(BookNotFoundException.class,() -> bookService.findById(invalidId));
    }
}
