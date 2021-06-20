package com.devdojo.renan.mapper;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.dto.AnimePostDTO;
import com.devdojo.renan.dto.AnimePutDTO;
import org.springframework.stereotype.Component;

//@Component
//@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    //Utilizando a lib mapstruct, esta classe realizará automaticamente a conversão de um DTO para um model.

    //public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimePostDTO animePostDTO);
    public abstract Anime toAnime(AnimePutDTO animePostDTO);

}
