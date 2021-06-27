package com.devdojo.renan.util;

import com.devdojo.renan.domain.Anime;
import com.devdojo.renan.dto.AnimePostDTO;

public class AnimePostDTOCreator {
    public static AnimePostDTO createAnimePostRequestBody() {
        return AnimePostDTO.builder()
                .name("Naruto")
                .build();
    }
}
