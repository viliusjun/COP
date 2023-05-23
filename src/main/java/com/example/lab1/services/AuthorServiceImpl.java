package com.example.lab1.services;

import com.example.lab1.mybatis.dao.AuthorMapper;
import com.example.lab1.mybatis.model.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorMapper authorMapper;

    @Override
    public String createAuthor(Author author) {
        authorMapper.insert(author);
        return "/authors?faces-redirect=true";
    }

    @Override
    public List<Author> selectAllAuthors() {
        return authorMapper.selectAll();
    }
}
