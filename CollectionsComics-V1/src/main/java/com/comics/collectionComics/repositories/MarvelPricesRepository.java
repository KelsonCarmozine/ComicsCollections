package com.comics.collectionComics.repositories;

import com.comics.collectionComics.models.MarvelPrices;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MarvelPricesRepository extends JpaRepository<MarvelPrices, Long> {
}
