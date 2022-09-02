package com.wspxxxx.bookstoremanager.controller;

import com.wspxxxx.bookstoremanager.dto.BookDTO;
import com.wspxxxx.bookstoremanager.dto.MessageResponseDTO;
import com.wspxxxx.bookstoremanager.service.BookService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import utils.BookUtils;

import java.awt.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static utils.BookUtils.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    public static final String BOOK_API_URL_PATH = "/api/v1/books";

    private MockMvc mockMvc ;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPostisCalledThenABookShouldBeCreated() throws Exception{
        BookDTO bookDTO = createFakeBookDTO();

        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book created with ID = "+bookDTO.getId())
                .build();
        when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);
        String expression;
        mockMvc.perform(post(BOOK_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));



    }

    @Test
    void testWhenPOSTwithInvalidISBNIsCalledThenBadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        bookDTO.setIsbn("invalid ISBN");

        mockMvc.perform(post(BOOK_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isOk());
    }
}
