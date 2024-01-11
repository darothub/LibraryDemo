package uk.gov.homeoffice.springtechtest.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    // add books field referencing Book entity
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Book> books;

    public User(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public User(String name) {
        this.name = name;
        books = List.of();
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books){
        this.books = books;
    }
}
