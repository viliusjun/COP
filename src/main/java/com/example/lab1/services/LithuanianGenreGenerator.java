package com.example.lab1.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class LithuanianGenreGenerator implements GenreGenerator, Serializable {
    private final static String[] genres = {
            "Biografija", "Dalykinė literatūra", "Detektyvinė literatūra", "Epas", "Erotinė literatūra",
            "Fantastinė literatūra", "Jaunimo literatūra", "Kelionių literatūra", "Legendos", "Marinistika",
            "Novelės", "Pasakos", "Poezija", "Publicistika", "Romanai", "Tragedijos", "Vaikų literatūra",
            "Absurdo drama", "Aforizmas", "Anekdotas", "Apybraiža", "Apysaka", "Apsakymas", "Atsiminimai",
            "Autobiografija", "Drama", "Epika", "Esė", "Fanfiction", "Feljetonas", "Graikų komedija", "Groteskas",
            "Humoras", "Idilė", "Kalambūras", "Komedija", "Kosminis siaubas", "Legenda", "Makama", "Moralitė",
            "Padavimas", "Pamfletas", "Panegirika", "Pasakėčia", "Pokštas", "Proza", "Recenzija", "Romanas",
            "Saga", "Siaubo literatūra", "Straipsnis", "Tragikomedija"};

    @Override
    public String generateGenre() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("Lithuanian genre generation");
        int generateRandomNumber = new Random().nextInt(genres.length);
        return genres[generateRandomNumber - 1];
    }
}
