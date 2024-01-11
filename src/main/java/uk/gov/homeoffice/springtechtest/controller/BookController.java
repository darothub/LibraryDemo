package uk.gov.homeoffice.springtechtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.homeoffice.springtechtest.models.dto.BookPayload;
import uk.gov.homeoffice.springtechtest.models.dto.UserPayload;
import uk.gov.homeoffice.springtechtest.service.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/books/{userId}")
    public ResponseEntity<BookPayload> createBook(@PathVariable("userId")Long userId,  @RequestBody BookPayload bookPayload) {
        return ResponseEntity.ok(bookService.createBook(userId, bookPayload));
    }
    @PostMapping("/{bookId}/books/{userId}")
    public ResponseEntity<BookPayload> borrowBook(@PathVariable("bookId")Long bookId, @PathVariable("userId")Long userId) {
        return ResponseEntity.ok(bookService.borrowBook(bookId, userId));
    }
}
