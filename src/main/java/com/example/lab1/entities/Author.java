package com.example.lab1.entities;

import javax.persistence.*;
import java.util.List;

import lombok.EqualsAndHashCode;

@Entity
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "select a from Author as a")
})
@Table(name = "AUTHOR")
@EqualsAndHashCode
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Basic(optional = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
