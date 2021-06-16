package com.devdojo.renan.controller;


import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.service.AnimeService;
import com.devdojo.renan.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

//RestController habilita o ResponseBody (Retorno dos recursos JSON STRING) X @Controller não habilita ResponseBody, sendo necessária atribuir essa annotation a cada recurso posteriormente.
@RestController
@RequestMapping("api/v1/animes") //declarado geralmente no plural
@Log4j2
@RequiredArgsConstructor //Cria constructor com os campos final,  injecao de dependencia, n precisando de autowired
public class AnimeController {

    private final DateUtil dateUtil;

    private final AnimeService animeService;

    @GetMapping
    public List<Anime> list(){
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return animeService.listaAll();
    }
}

//shift + f10 = executar
//shift + f09 = debug
//ctrl + f9 = build
//Se faço uma modificação dentro de um método, basta ir em build project para executar a aplicação, sendo desnecessário uma nova execução da aplicação, graças ao how swap, vindo do spring-devtools(ArtifactId).