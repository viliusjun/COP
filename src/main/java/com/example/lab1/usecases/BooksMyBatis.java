package com.example.lab1.usecases;


import com.example.lab1.mybatis.dao.BookMapper;
import com.example.lab1.mybatis.model.Author;
import com.example.lab1.mybatis.model.Book;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public class BooksMyBatis {
    @Inject
    private BookMapper bookMapper;

    @Getter
    private List<Book> allBooks;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @Getter @Setter
    private Author author;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = bookMapper.selectAuthor(authorId);
    }

//    private void loadAllBooks() {
//        this.allBooks = bookMapper.selectAll();
//    }

    @Transactional
    public String createBook() {
        bookMapper.insert(bookToCreate);
        return "/myBatis/books?faces-redirect=true";
    }
}
