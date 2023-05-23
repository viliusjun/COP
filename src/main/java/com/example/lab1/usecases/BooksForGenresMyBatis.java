package com.example.lab1.usecases;

import com.example.lab1.interceptors.LoggedInvocation;
import com.example.lab1.mybatis.dao.BookMapper;
import com.example.lab1.mybatis.dao.GenreMapper;
import com.example.lab1.mybatis.model.Book;
import com.example.lab1.mybatis.model.Genre;
import com.example.lab1.services.GenreGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Model
@SessionScoped
@Named
public class BooksForGenresMyBatis implements Serializable {
    @Inject
    private BookMapper bookMapper;


    @Inject
    private GenreMapper genreMapper;

    @Inject
    private GenreGenerator genreGenerator;

    private CompletableFuture<String> genreGenerationTask = null;

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
    @LoggedInvocation
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

        return "/genres?bookId=" + currentOpenedBook.getId() + "&faces-redirect=true";
    }

    @LoggedInvocation
    public String generateGenre() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        genreGenerationTask = CompletableFuture.supplyAsync(() -> genreGenerator.generateGenre());

        return  "/genres.xhtml?faces-redirect=true&bookId=" + requestParameters.get("bookId");
    }

    public boolean isGenreGenerationRunning() {
        return genreGenerationTask != null && !genreGenerationTask.isDone();
    }

    public String getGenreGenerationStatus() throws ExecutionException, InterruptedException {
        if (genreGenerationTask == null) {
            return null;
        } else if (isGenreGenerationRunning()) {
            return "Genre generation in progress";
        }
        return "Possible genre: " + genreGenerationTask.get();
    }
}
