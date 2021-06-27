package com.devdojo.renan.service;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.exception.BadRequestException;
import com.devdojo.renan.repository.AnimeRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {
    @InjectMocks //Se utiliza quando se quer testar a classe em sí
    private AnimeService animeService;
    @Mock //Utiliza para todas as classes que estão sendo utilizadas dentro da classe (AnimeController)
    //Define o comportamento(mock) das classes do injetadas
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
        //Executa antes de cada teste
    void setUp() {
        //Para testar o método de paginação, se faz necessário mockar seu comportamento
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createdValidAnime()));
        //Explicação linha BDDMockito: Quando for executado o método listAll da classe de service,
        //  não importando o argumento passado,então retorne o PageImpl<Anime>  animePage
        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepositoryMock.findAll())
                .thenReturn(List.of(AnimeCreator.createdValidAnime()));

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AnimeCreator.createdValidAnime()));

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createdValidAnime()));

        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createdValidAnime());

        BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));

    }

    @Test
    @DisplayName("ListAll returns list of anime inside page object when successful")
    void listAll_returnsListOfAnimesInsidePageObject_WhenSuccessful() {
        String expectedName = AnimeCreator.createdValidAnime().getName();
        Page<Anime> animePage = animeService.listAll(PageRequest.of(1, 1));

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("listAllNonPageable returns list of anime  when successful")
    void listAllNonPageable_returnsListOfAnimes_WhenSuccessful() {
        String expectedName = AnimeCreator.createdValidAnime().getName();
        List<Anime> animes = animeService.ListAllNonPageble();

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("findByIdOrElseThrow returns a anime when successful")
    void findByIdOrElseThrow_returnsAnime_WhenSuccessful() {
        Long expectedId = AnimeCreator.createdValidAnime().getId();
        Anime anime = animeService.findByIdOrElseThrow(1);

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByIdOrElseThrow throws a BAD REQUEST EXCEPTION when anime is not found")
    void findByIdOrElseThrow_ThrowsBadRequestException_WhenAnimeNotFound() {
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> animeService.findByIdOrElseThrow(1));
    }


    @Test
    @DisplayName("FindByName returns list of anime when successful")
    void FindByName_returnsAnimeList_WhenSuccessful() {
        String expectedName = AnimeCreator.createdValidAnime().getName();
        List<Anime> animes = animeService.findByName("anime");

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("save returns a anime when successful")
    void save_returnsAnime_WhenSuccessful() {
        Anime anime = animeService.save(AnimePostDTOCreator.createAnimePostRequestBody());
        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createdValidAnime());
    }

    @Test
    @DisplayName("replace updates a anime when successful")
    void replace_updatesAnime_WhenSuccessful() {
        //replace (update) não retorna um objeto válido, portanto valida-se se não gerou exception e o status code
        Assertions.assertThatCode(() -> animeService.replace(AnimePutDTOCreator.createAnimePutRequestBody())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete removes anime when successful")
    void delete_Anime_WhenSuccessful() {
        Assertions.assertThatCode(() -> animeService.delete(1)).doesNotThrowAnyException();
    }

}