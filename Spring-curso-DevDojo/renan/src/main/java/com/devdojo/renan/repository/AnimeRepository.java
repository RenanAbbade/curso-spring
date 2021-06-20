package com.devdojo.renan.repository;

import com.devdojo.renan.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
