package com.devdojo.renan.controller;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.dto.AnimePostDTO;
import com.devdojo.renan.dto.AnimePutDTO;
import com.devdojo.renan.service.AnimeService;
import com.devdojo.renan.util.AnimeCreator;
import com.devdojo.renan.util.AnimePostDTOCreator;
import com.devdojo.renan.util.AnimePutDTOCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//Para testes no Spring, pode-se utilizar o @SpringBootTest,
// como maleficio, este tenta inicializar
// todo o contexto de aplicação do Spring
// Sendo assim, não funcionária com um BD in memory por exemplo.
//Utilizing o @ExtendWith(SpringExtension.class) não é necessário utilizar o context 100 %
@ExtendWith(SpringExtension.class)
class AnimeControllerTest {
    @InjectMocks //Se utiliza quando se quer testar a classe em sí
    private AnimeController animeController;
    @Mock //Utiliza para todas as classes que estão sendo utilizadas dentro da classe (AnimeController)
    //Define o comportamento(mock) das classes do injetadas
    private AnimeService animeServiceMock;

    @BeforeEach
        //Executa antes de cada teste
    void setUp() {
        //Para testar o método de paginação, se faz necessário mockar seu comportamento
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createdValidAnime()));
        //Explicação linha BDDMockito: Quando for executado o método listAll da classe de service,
        //  não importando o argumento passado,então retorne o PageImpl<Anime>  animePage
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMock.ListAllNonPageble())
                .thenReturn(List.of(AnimeCreator.createdValidAnime()));

        BDDMockito.when(animeServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AnimeCreator.createdValidAnime());

        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createdValidAnime()));

        BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimePostDTO.class)))
                .thenReturn(AnimeCreator.createdValidAnime());

        //Como replace retorna void, os métodos a serem chamados devem inclinar para não esperar um retorno
        BDDMockito.doNothing().when(animeServiceMock).replace(ArgumentMatchers.any(AnimePutDTO.class));
        BDDMockito.doNothing().when(animeServiceMock).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("List returns list of anime inside page object when successful")
    void list_returnsListOfAnimesInsidePageObject_WhenSuccessful() {
        String expectedName = AnimeCreator.createdValidAnime().getName();
        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("returns list of anime  when successful")
    void listAll_returnsListOfAnimes_WhenSuccessful() {
        String expectedName = AnimeCreator.createdValidAnime().getName();
        List<Anime> animes = animeController.listAll().getBody();

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("FindById returns a anime when successful")
    void findById_returnsAnime_WhenSuccessful() {
        Long expectedId = AnimeCreator.createdValidAnime().getId();
        Anime anime = animeController.findById(1).getBody();

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("FindByName returns list of anime when successful")
    void FindByName_returnsAnimeList_WhenSuccessful() {
        String expectedName = AnimeCreator.createdValidAnime().getName();
        List<Anime> animes = animeController.findByName("anime").getBody();

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("save returns a anime when successful")
    void save_returnsAnime_WhenSuccessful() {
        Anime anime = animeController.save(AnimePostDTOCreator.createAnimePostRequestBody()).getBody();
        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createdValidAnime());
    }

    @Test
    @DisplayName("replace updates a anime when successful")
    void replace_updatesAnime_WhenSuccessful() {
        //replace (update) não retorna um objeto válido, portanto valida-se se não gerou exception e o status code
        Assertions.assertThatCode(() -> animeController.replace(AnimePutDTOCreator.createAnimePutRequestBody())).doesNotThrowAnyException();
        ResponseEntity<Void> entity = animeController.replace(AnimePutDTOCreator.createAnimePutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Delete removes anime when successful")
    void delete_Anime_WhenSuccessful() {
        //replace (update) não retorna um objeto válido, portanto valida-se se não gerou exception e o status code
        Assertions.assertThatCode(() -> animeController.delete(1)).doesNotThrowAnyException();
        ResponseEntity<Void> entity = animeController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}