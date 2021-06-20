package com.devdojo.renan.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostDTO { //DTOClass
    //Validando campos obrigatórios com spring-boot-starter-validation
    @NotEmpty(message = "The name of the anime cannot be empty")
    @NotNull(message = "The name of the anime cannot be null")
    private String name;
}
