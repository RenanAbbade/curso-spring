package com.devdojo.renan.util;

import com.devdojo.renan.domain.Anime;

//Classe para criar todos os objetos que são utilzados pelos testes
public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder().name("Naruto Shippuden").build();
    }

    public static Anime createdValidAnime() {
        return Anime.builder().name("Naruto Shippuden").id(1L).build();
    }

    public static Anime createdValidUpdatedAnime() {
        return Anime.builder().name("One Piece").id(1L).build();
    }
}
