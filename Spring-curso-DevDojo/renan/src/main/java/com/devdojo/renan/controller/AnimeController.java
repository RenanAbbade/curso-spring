package com.devdojo.renan.controller;


import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.dto.AnimePostDTO;
import com.devdojo.renan.dto.AnimePutDTO;
import com.devdojo.renan.service.AnimeService;
import com.devdojo.renan.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

//RestController habilita o ResponseBody (Retorno dos recursos JSON STRING) X @Controller não habilita ResponseBody, sendo necessária atribuir essa annotation a cada recurso posteriormente.
@RestController
@RequestMapping("api/v1/animes") //declarado geralmente no plural
@Log4j2
@RequiredArgsConstructor //Cria constructor com os campos final,  injecao de dependencia, n precisando de autowired
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<Anime>> list(Pageable pageable){
        return ResponseEntity.ok(animeService.listAll(pageable));
    }//http://localhost:8080/api/v1/animes?size=5

    @GetMapping("/all")
    public ResponseEntity<List<Anime>> listAll(){
        return ResponseEntity.ok(animeService.ListAllNonPageble());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return ResponseEntity.ok(animeService.findByIdOrElseThrow(id));
    }

    //Pegando o usuario autenticado na session
    @GetMapping(path = "by-id/{id}")
    public ResponseEntity<Anime> findByAuthenticationPrincipal(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails){
        log.info(userDetails);
        return ResponseEntity.ok(animeService.findByIdOrElseThrow(id));
    }

    @GetMapping(path = "/findByName") //findByName?name=Naruto
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        return ResponseEntity.ok(animeService.findByName(name));
    }

    //Como o método save pode afetar a integridade do bd da aplicação
    //Se faz necessário a utilização de segurança a nivel de método por role
    //@PreAuthorize(role)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostDTO animePostDTO){ //Inserindo o @Valid, indicando ao Spring que este objeto contem validações em seus campos
        return new ResponseEntity(animeService.save(animePostDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutDTO animePutDTO){
        animeService.replace(animePutDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

//shift + f10 = executar
//shift + f09 = debug
//ctrl + f9 = build
//Se faço uma modificação dentro de um método, basta ir em build project para executar a aplicação, sendo desnecessário uma nova execução da aplicação, graças ao how swap, vindo do spring-devtools(ArtifactId).