package com.example.lab1.usecases;

import com.example.lab1.mybatis.dao.GenreMapper;
import com.example.lab1.mybatis.model.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class GenresMyBatis {
    @Inject
    private GenreMapper genreMapper;

    @Getter
    private List<Genre> allGenres;

    @Getter @Setter
    private Genre genreToCreate = new Genre();



    @PostConstruct
    public void init() {
        this.loadAllGenres();
    }

    private void loadAllGenres() {
        this.allGenres = genreMapper.selectAll();
    }

    @Transactional
    public String createGenre() {
        genreMapper.insert(genreToCreate);
        return "/authors?faces-redirect=true";
    }


}