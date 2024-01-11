package uk.gov.homeoffice.springtechtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.gov.homeoffice.springtechtest.models.entities.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
    List<Book> findBookByReturnDateGreaterThan(LocalDate returnDate);
    @Query("SELECT b FROM Book b WHERE b.returnDate > CURRENT_DATE")
    List<Book> getReturnDatedBook();
}
