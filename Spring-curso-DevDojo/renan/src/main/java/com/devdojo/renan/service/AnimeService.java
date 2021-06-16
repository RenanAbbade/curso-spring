package com.devdojo.renan.service;

import com.devdojo.renan.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    public List<Anime> listaAll(){
        return List.of(new Anime(1L, "Naruto"), new Anime(2L, "Berserk"));
    }
}
