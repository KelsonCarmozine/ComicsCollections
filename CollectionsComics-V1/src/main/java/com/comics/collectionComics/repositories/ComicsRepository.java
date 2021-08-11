package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.Comic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComicsRepository extends JpaRepository<Comic, Long> {
}
