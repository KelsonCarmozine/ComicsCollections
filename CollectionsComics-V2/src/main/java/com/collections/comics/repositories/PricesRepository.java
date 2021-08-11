package com.collections.comics.repositories;

import com.collections.comics.models.Price;
import org.springframework.data.repository.CrudRepository;


public interface PricesRepository extends CrudRepository<Price, Long> {
}
