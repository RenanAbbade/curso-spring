package com.devdojo.renan.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Anime {

    private String name;

    public Anime(String name) {
        this.name = name;
    }

}

