package com.devdojo.renan.controller;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.service.AnimeService;
import com.devdojo.renan.util.AnimeCreator;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach //Executa antes de cada teste
    void setUp(){
        //Para testar o método de paginação, se faz necessário mockar seu comportamento
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createdValidAnime()));
        //Explicação linha BDDMockito: Quando for executado o método listAll da classe de service,
        //  não importando o argumento passado,então retorne o PageImpl<Anime>  animePage
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMock.ListAllNonPageble())
                .thenReturn(List.of(AnimeCreator.createdValidAnime()));
    }

    @Test
    @DisplayName("List returns list of anime inside page object when successful")
    void list_returnsListOfAnimesInsidePageObject_WhenSuccessful(){
        String expectedName = AnimeCreator.createdValidAnime().getName();
        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("returns list of anime  when successful")
    void listAll_returnsListOfAnimes_WhenSuccessful(){
        String expectedName = AnimeCreator.createdValidAnime().getName();
        List<Anime> animes = animeController.listAll().getBody();

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

}