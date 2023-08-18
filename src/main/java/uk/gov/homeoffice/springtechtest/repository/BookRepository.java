package uk.gov.homeoffice.springtechtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.homeoffice.springtechtest.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
