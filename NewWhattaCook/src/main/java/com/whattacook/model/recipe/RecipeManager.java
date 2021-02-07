package com.whattacook.model.recipe;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RecipeManager extends ReactiveMongoRepository<Recipe, String>{
	
	Mono<Boolean> existsByTitleIgnoreCase(String title);
	
	Flux<Recipe> findByTitleContainingIgnoreCase(String title);

}
