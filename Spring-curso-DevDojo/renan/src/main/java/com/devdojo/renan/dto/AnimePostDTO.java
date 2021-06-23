package com.devdojo.renan.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostDTO { //DTOClass
    //Validando campos obrigatórios com spring-boot-starter-validation
    @NotEmpty(message = "The name of the anime cannot be empty")
    @NotNull(message = "The name of the anime cannot be null")
    @JsonProperty("name")
    private String name;
}
