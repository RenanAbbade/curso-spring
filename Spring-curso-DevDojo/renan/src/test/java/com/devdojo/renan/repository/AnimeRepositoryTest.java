package com.devdojo.renan.repository;

import com.devdojo.renan.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@DisplayName("Tests for anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Saves persist anime when successful")
    void save_PersistAnime_WhenSuccessful(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
        Anime animeToBeSaved = createdAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull(); //org.assertj
        Assertions.assertThat(animeSaved.getId()).isNotNull(); //Verifico se o id foi automaticamente gerado como esperado
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());//Verifico se o name do anime a ser salvo é o mesmo que solicitei.
    }

//    @Test
//    @DisplayName("Saves update`s anime when successful")
//    void save_PersistAnime_WhenSuccessful(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
//        Anime animeToBeSaved = createdAnime();
//        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
//
//        Assertions.assertThat(animeSaved).isNotNull(); //org.assertj
//        Assertions.assertThat(animeSaved.getId()).isNotNull(); //Verifico se o id foi automaticamente gerado como esperado
//        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());//Verifico se o name do anime a ser salvo é o mesmo que solicitei.
//    }

    private Anime createdAnime(){
        return Anime.builder().name("Naruto Shippuden").build();
    }

}