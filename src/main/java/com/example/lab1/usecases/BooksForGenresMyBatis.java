package com.example.lab1.usecases;

import com.example.lab1.mybatis.dao.BookMapper;
import com.example.lab1.mybatis.dao.GenreMapper;
import com.example.lab1.mybatis.model.Author;
import com.example.lab1.mybatis.model.Book;
import com.example.lab1.mybatis.model.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model
public class BooksForGenresMyBatis {
    @Inject
    private BookMapper bookMapper;


    @Inject
    private GenreMapper genreMapper;

    @Getter @Setter
    private List<Genre> bookGenres;

    @Getter @Setter
    private Genre genreToCreate = new Genre();

    @Getter @Setter
    private Book currentOpenedBook;


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long bookId = Long.parseLong(requestParameters.get("bookId"));
        this.bookGenres = bookMapper.selectGenresForBook(bookId);
        this.currentOpenedBook = bookMapper.selectByPrimaryKey(bookId);
    }

    @Transactional
    public String createGenreForBook() {
        Genre existingGenre = genreMapper.selectByName(genreToCreate.getName());
        if (existingGenre == null) {
            genreMapper.insert(genreToCreate);
        } else {
            genreToCreate = existingGenre;
        }

        bookGenres.add(genreToCreate);
        currentOpenedBook.setGenres(bookGenres);
        bookMapper.updateByPrimaryKey(currentOpenedBook);

        Map<String, Long> params = new HashMap<>();
        params.put("books_id", currentOpenedBook.getId());
        params.put("genres_id", genreToCreate.getId());
        genreMapper.insertBookGenre(params);

        String returnUrl = "/genres?bookId=" + currentOpenedBook.getId() + "&faces-redirect=true";
        return returnUrl;
    }
}
