package com.devdojo.renan.service;

import com.devdojo.renan.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {

    private List<Anime> animes = List.of(new Anime(1L, "Naruto"), new Anime(2L, "Berserk"));

    public List<Anime> listaAll() {
        return animes;
    }

    public Anime findById(long id) {
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime  not found"));
    }//Se não for achado o id passado no path, retorna-se uma badRequest
}