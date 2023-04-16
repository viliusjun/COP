package com.example.lab1.usecases;


import com.example.lab1.mybatis.dao.BookMapper;
import com.example.lab1.mybatis.dao.GenreMapper;
import com.example.lab1.mybatis.model.Author;
import com.example.lab1.mybatis.model.Book;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class BooksMyBatis {
    @Inject
    private BookMapper bookMapper;

    @Getter @Setter
    private List<Book> allBooks;

    @PostConstruct
    public void init() {
        this.loadAllBooks();
    }

    private void loadAllBooks() {
        this.allBooks = bookMapper.selectAll();
    }
}
