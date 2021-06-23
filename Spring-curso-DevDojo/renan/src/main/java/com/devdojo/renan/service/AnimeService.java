package com.devdojo.renan.service;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.dto.AnimePostDTO;
import com.devdojo.renan.dto.AnimePutDTO;
import com.devdojo.renan.exception.BadRequestException;
import com.devdojo.renan.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> ListAllNonPageble() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime  not found"));

    }//Se não for achado o id passado no path, retorna-se uma badRequest

    // @Transactional: Com essa annotation eu tenho rollback automatico em uma transação envolvendo o BD.
    // De modo que se alterar o BD e após isso a aplicação retornar uma exception antes do método ser retornado,
    // as alterações em BD não serão commitadas. Com rollbackFor ele leva as Exceptions em runtime em consideração
    @Transactional
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