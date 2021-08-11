package com.collections.comics.repositories;

import com.collections.comics.models.Creator;
import org.springframework.data.repository.CrudRepository;


public interface CreatorsRepository extends CrudRepository<Creator, Long> {
}
