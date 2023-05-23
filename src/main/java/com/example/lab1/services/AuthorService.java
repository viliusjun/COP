package com.example.lab1.services;

import com.example.lab1.mybatis.model.Author;

import java.util.List;

public interface AuthorService {
    String createAuthor(Author author);

    List<Author> selectAllAuthors();
}
