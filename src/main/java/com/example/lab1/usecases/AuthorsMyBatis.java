package com.example.lab1.usecases;


import com.example.lab1.interceptors.LoggedInvocation;
import com.example.lab1.mybatis.model.Author;
import com.example.lab1.services.AuthorService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AuthorsMyBatis {
    @Inject
    private AuthorService authorService;

    @Getter
    private List<Author> allAuthors;

    @Getter @Setter
    private Author authorToCreate = new Author();

    @PostConstruct
    public void init() {
        this.loadAllAuthors();
    }

    private void loadAllAuthors() {
        this.allAuthors = authorService.selectAllAuthors();
    }

    @Transactional
    @LoggedInvocation
    public String createAuthor() {
        return authorService.createAuthor(authorToCreate);
    }
}
