package com.devdojo.renan.client;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.dto.AnimePostDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        //O Rest template realiza uma request mediante método http,
        // e pode mapear o objeto via jackson de acordo com o
        // .class passado no segundo parametro
        // o id está sendo passado como 1, no terceiro parametro
        /*
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/api/v1/animes/1", Anime.class);
        log.info(entity);

        //Retornando diretamente um object
        Anime anime = new RestTemplate().getForObject("http://localhost:8080/api/v1/animes/{id}", Anime.class, 10);

        //Rest Template Exchange
        //Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/api/v1/animes/all", Anime[].class); //Utilizando Array

        //Utilizando super type token (Realiza uma conversão por meio do método exchange) sem necessidade de casting
        ResponseEntity<List<Anime>> animesList = new RestTemplate().exchange("http://localhost:8080/api/v1/animes/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
        }); //Utilizando Array

        log.info(animesList.getBody());
*/
        //POST
        Anime anime = Anime.builder().name("Kingdom").build();

        //Anime animeResponse = new RestTemplate().postForObject("http://localhost:8080/api/v1/animes/",anime, Anime.class); //URL, Objeto request, objeto Response.class

        //POST Exchange -> Posso enviar headers http no exchange
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Anime> animeResponse = new RestTemplate().exchange("http://localhost:8080/api/v1/animes/", HttpMethod.POST, new HttpEntity<>(anime, httpHeaders), Anime.class); //URL, Objeto request, objeto Response.class

        log.info(animeResponse);
    }
}
