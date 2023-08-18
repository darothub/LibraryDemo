package uk.gov.homeoffice.springtechtest.models.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookPayload {

    private Long id;

    private String title;

    private String author;

    private Boolean onLoan;

    private LocalDateTime returnDate;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean isOnLoan() {
        return onLoan;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public BookPayload(Long id, String title, String author, Boolean onLoan, LocalDateTime returnDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.onLoan = onLoan;
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookPayload that = (BookPayload) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(author, that.author)) return false;
        if (!Objects.equals(onLoan, that.onLoan)) return false;
        return Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (onLoan != null ? onLoan.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }
}
