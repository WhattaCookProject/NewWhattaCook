package com.whattacook.model.ingredient;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface IngredientManager extends ReactiveMongoRepository<Ingredient, String>{
	
	Mono<Boolean> existsByNameIgnoreCase(String name);
	
	Mono<Ingredient> findByNameIgnoreCase(String name);

}
