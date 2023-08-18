package uk.gov.homeoffice.springtechtest.service;

import org.springframework.stereotype.Service;
import uk.gov.homeoffice.springtechtest.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
