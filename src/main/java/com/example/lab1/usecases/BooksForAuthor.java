package com.example.lab1.usecases;

import com.example.lab1.entities.Author;
import com.example.lab1.entities.Book;
import com.example.lab1.interceptors.LoggedInvocation;
import com.example.lab1.persistence.AuthorsDAO;
import com.example.lab1.persistence.BooksDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class BooksForAuthor implements Serializable {

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter @Setter
    private Author author;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
    }

    @Transactional
    @LoggedInvocation
    public void createBook() {
        bookToCreate.setAuthor(this.author);
        booksDAO.persist(bookToCreate);
    }
}
