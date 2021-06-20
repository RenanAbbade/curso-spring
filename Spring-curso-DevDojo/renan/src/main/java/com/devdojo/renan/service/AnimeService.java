package com.devdojo.renan.service;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.dto.AnimePostDTO;
import com.devdojo.renan.dto.AnimePutDTO;
import com.devdojo.renan.exception.BadRequestException;
import com.devdojo.renan.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> listaAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime  not found"));

    }//Se não for achado o id passado no path, retorna-se uma badRequest

    public Anime save(AnimePostDTO anime) {
        return animeRepository.save(Anime.builder().name(anime.getName()).build());

    }

    public void delete(long id) {
        animeRepository.delete(findById(id));
    }

    public void replace(AnimePutDTO animePutDTO) {
        Anime savedAnime = findById(animePutDTO.getId());
        animeRepository.save(Anime.builder().id(savedAnime.getId()).name(animePutDTO.getName()).build());
    }
}