package uk.gov.homeoffice.springtechtest.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Boolean isOnLoan() {
        return returnDate != null;
    }

    public Book(String title, String author, LocalDate returnDate) {
        this.title = title;
        this.author = author;
        this.returnDate = returnDate;
    }

    public boolean isLateReturned(LocalDate dateReturned) {
        return this.returnDate.isBefore(dateReturned);
    }
    public Book() {
    }
}
