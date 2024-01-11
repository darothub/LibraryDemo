package uk.gov.homeoffice.springtechtest.service;

import org.springframework.stereotype.Service;
import uk.gov.homeoffice.springtechtest.models.dto.BookPayload;
import uk.gov.homeoffice.springtechtest.models.entities.Book;
import uk.gov.homeoffice.springtechtest.models.entities.User;
import uk.gov.homeoffice.springtechtest.repository.BookRepository;
import uk.gov.homeoffice.springtechtest.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public BookPayload createBook(Long userId, BookPayload bookPayload) {
        User user = bookRepository.findByUserId(userId).get(0).getUser();

        Book newBook = new Book(
                bookPayload.getTitle(),
                bookPayload.getAuthor(),
                bookPayload.getReturnDate().toLocalDate()
        );
        newBook.setUser(user);
        newBook = bookRepository.save(newBook);
        return new BookPayload(newBook.getId(), newBook.getTitle(), newBook.getAuthor(), newBook.isOnLoan(), newBook.getReturnDate().atStartOfDay());
    }

    public List<BookPayload> createBooks(Long userId, List<BookPayload> bookPayloads) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Book> books = bookPayloads.stream().map(bookPayload -> {
            Book newBook = new Book(
                    bookPayload.getTitle(),
                    bookPayload.getAuthor(),
                    bookPayload.getReturnDate().toLocalDate()
            );
            newBook.setUser(user);
            return newBook;
        }).toList();
        bookRepository.saveAll(books);
        return bookPayloads;
    }

    public BookPayload borrowBook(Long bookId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(()  -> new RuntimeException("Book not found"));
        book.setUser(user);
        book.setReturnDate(LocalDate.now().plusDays(20));
        bookRepository.save(book);
        List<Book> otherBooks = bookRepository.getReturnDatedBook();
        System.out.println("Elements of the List:");
        otherBooks.forEach(System.out::println);
        return new BookPayload(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.isOnLoan(),
                book.getReturnDate().atStartOfDay()
        );
    }

}
