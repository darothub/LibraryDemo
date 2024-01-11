package uk.gov.homeoffice.springtechtest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.homeoffice.springtechtest.models.dto.BookPayload;
import uk.gov.homeoffice.springtechtest.models.entities.Book;
import uk.gov.homeoffice.springtechtest.models.entities.User;
import uk.gov.homeoffice.springtechtest.repository.BookRepository;
import uk.gov.homeoffice.springtechtest.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTwoTest {

    @Mock
    BookRepository bookRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    BookService bookService;

    private BookPayload bookPayload;
    private Book book;
    private User user;

    @BeforeEach
    public void setup(){
        user = new User("User");
        bookPayload = new BookPayload(
                11L,
                "The patient bird",
                "Hardley Chase",
                null,
                LocalDateTime.now().minusDays(15)
        );
        book = new Book(
                bookPayload.getTitle(),
                bookPayload.getAuthor(),
                bookPayload.getReturnDate().toLocalDate()
        );
    }
    @Test
    public void createBook() {
        List<Book> books = List.of(book);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(bookRepository.saveAll(anyList())).thenReturn(books);
        books.forEach(book1 -> book1.setUser(user));
        List<BookPayload> bookPayloads = bookService.createBooks(1L, List.of(bookPayload));

        assertDoesNotThrow(() -> new RuntimeException("User not found"));
        assertArrayEquals(bookPayloads.toArray(), List.of(bookPayload).toArray());
        assertEquals(user.getId(), books.get(0).getUser().getId());
    }

    @Test
    public void given_an_invalid_userId_throw() {
        doThrow(new RuntimeException("User not found"))
                .when(userRepository).findById(113L);
        assertThrows(RuntimeException.class, () -> bookService.createBooks(113L, List.of(bookPayload)));
    }
}
