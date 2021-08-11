package com.collections.comics.repositories;

import com.collections.comics.models.Comic;
import org.springframework.data.repository.CrudRepository;


public interface ComicsRepository extends CrudRepository<Comic, Long> {
}
