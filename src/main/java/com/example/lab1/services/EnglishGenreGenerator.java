package com.example.lab1.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class EnglishGenreGenerator implements GenreGenerator, Serializable {
    private final static String[] genres = {
            "Biography", "Non-fiction", "Detective fiction", "Epics", "Erotic literature",
            "Fantasy literature", "Young adult literature", "Travel literature", "Legends", "Marinism",
            "Short stories", "Fairy tales", "Poetry", "Journalism", "Novels", "Tragedies", "Children's literature",
            "Absurdist drama", "Aphorism", "Anecdote", "Caricature", "Short story", "Narrative", "Memoirs",
            "Autobiography", "Drama", "Epic literature", "Essay", "Fanfiction", "Feuilleton", "Ancient Greek comedy",
            "Grotesque", "Humor", "Idyll", "Calambur", "Comedy", "Cosmic horror", "Legend", "Makama", "Morality play",
            "Narrative", "Pamphlet", "Panegyric", "Fairy tale", "Joke", "Prose", "Review", "Novel", "Saga",
            "Horror literature", "Article", "Tragicomedy"
    };

    @Override
    public String generateGenre() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("English genre generation");
        int generateRandomNumber = new Random().nextInt(genres.length);
        return genres[generateRandomNumber - 1];
    }
}
