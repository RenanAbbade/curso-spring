package com.devdojo.renan.util;

import com.devdojo.renan.dto.AnimePostDTO;
import com.devdojo.renan.dto.AnimePutDTO;

public class AnimePutDTOCreator {
    public static AnimePutDTO createAnimePutRequestBody() {
        return AnimePutDTO.builder()
                .name(AnimeCreator.createdValidUpdatedAnime().getName())
                .id(AnimeCreator.createdValidUpdatedAnime().getId())
                .build();
    }
}
