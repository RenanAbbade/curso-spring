package com.devdojo.renan.repository;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.util.AnimeCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for anime repository")
@Log4j2
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Saves persist anime when successful")
    void save_PersistAnime_WhenSuccessful(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull(); //org.assertj
        Assertions.assertThat(animeSaved.getId()).isNotNull(); //Verifico se o id foi automaticamente gerado como esperado
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());//Verifico se o name do anime a ser salvo é o mesmo que solicitei.
    }

    @Test
    @DisplayName("Saves update`s anime when successful")
    void save_UpdateAnime_WhenSuccessful(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        //update flux
        animeSaved.setName("Mushouku Tensei");
        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull(); //org.assertj
        Assertions.assertThat(animeUpdated.getId()).isNotNull(); //Verifico se o id foi automaticamente gerado como esperado
        Assertions.assertThat(animeUpdated.getName()).isEqualToIgnoringCase("Mushouku Tensei");//Verifico se o name do anime a ser salvo é o mesmo que solicitei.
    }

    @Test
    @DisplayName("Delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

       //delete flux
        this.animeRepository.delete(animeSaved);

        //Como o método de delete retorna Void, para validar se o argumento foi deltado, deve-se busca-lo por id.
        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();

    }

    @Test
    @DisplayName("Find by name, returns a List of anime when successful")
    void findByName_ReturnsListAnime_WhenSuccessful(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        List<Anime> animes = this.animeRepository.findByName(animeSaved.getName());

        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(animeSaved);

    }


    @Test
    @DisplayName("Find by name, returns a empty List  when no object is found")
    void findByName_ReturnsEmptyList_WhenAnimeNotFound(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
        List<Anime> animes = this.animeRepository.findByName("xxx");

        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintValidationException when name is empty")
    void save_ThrowConstraintValidationException_WhenNameIsEmpty(){//Pattern de nomeação do metódo de testes: nomeMetodo_OqueDeveFazer_ResultadoEsperado
        Anime anime = new Anime();
        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
                .isInstanceOf(ConstraintViolationException.class);

        //outra forma seria:
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The Anime cannot be empty");
    }



}