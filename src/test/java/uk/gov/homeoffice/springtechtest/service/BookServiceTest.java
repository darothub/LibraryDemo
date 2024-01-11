package uk.gov.homeoffice.springtechtest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import uk.gov.homeoffice.springtechtest.models.dto.BookPayload;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookServiceTest {

    @Autowired
    BookService bookService;
    @DisplayName("Create book")
    @Test
    public void createBook() {
        BookPayload bookPayload = new BookPayload(
                11L,
                "The patient bird",
                "Hardley Chase",
                null,
                LocalDateTime.now().minusDays(15)
        );
        BookPayload result = bookService.createBook(1L, bookPayload);

        assertNotNull(result);
        assertEquals(11L, result.getId());
        assertEquals("Hardley Chase", result.getAuthor());
    }
}
