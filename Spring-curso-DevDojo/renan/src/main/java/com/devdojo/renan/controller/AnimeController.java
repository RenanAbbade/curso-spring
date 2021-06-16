package com.devdojo.renan.controller;


import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

//RestController habilita o ResponseBody (Retorno dos recursos JSON STRING) X @Controller não habilita ResponseBody, sendo necessária atribuir essa annotation a cada recurso posteriormente.
@RestController
@RequestMapping("api/v1/anime")
@Log4j2
@RequiredArgsConstructor //Cria constructor com os campos final, se tratando de uma simples injecao de dependencia, n precisa de autowired
public class AnimeController {

    private final DateUtil dateUtil;

    @GetMapping("list")
    public List<Anime> list(){
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return List.of(new Anime("Naruto"), new Anime("Berserk"));
    }
}

