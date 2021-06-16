package com.devdojo.renan.domain;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data //Já gera equals, hashCode, toString, getters and setters
public class Anime {

    private Long id;
    private String name;

}

