package com.example.lab1.usecases;

import com.example.lab1.mybatis.dao.GenreMapper;
import com.example.lab1.mybatis.model.Book;
import com.example.lab1.mybatis.model.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
public class GenresForBooksMyBatis {
    @Inject
    private GenreMapper genreMapper;

    @Getter @Setter
    private Genre currentOpenedGenre;

    @Getter @Setter
    private List<Book> genreBooks;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long genreId = Long.parseLong(requestParameters.get("genreId"));
        this.genreBooks = genreMapper.selectBooksForGenre(genreId);
        this.currentOpenedGenre = genreMapper.selectByPrimaryKey(genreId);
    }
}
