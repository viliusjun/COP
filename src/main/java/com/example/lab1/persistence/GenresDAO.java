package com.example.lab1.persistence;

import com.example.lab1.entities.Book;
import com.example.lab1.entities.Genre;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class GenresDAO {
    @Inject
    private EntityManager em;

    public void persist(Genre genre){
        this.em.persist(genre);
    }

    public Genre findOne(Integer id){
        return em.find(Genre.class, id);
    }

    public Genre update(Genre genre){
        return em.merge(genre);
    }
}