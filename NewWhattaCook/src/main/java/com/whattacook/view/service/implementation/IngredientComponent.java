package com.whattacook.view.service.implementation;

import static com.whattacook.util.exceptions.IngredientExceptions.throwsUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.model.ingredient.IngredientManager;
import com.whattacook.util.exceptions.IngredientExceptions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
class IngredientComponent {
	
	@Autowired
	private IngredientManager manager;

	Flux<IngredientJson> findAllIngredients() throws IngredientExceptions {
		return manager.findAll()
				.switchIfEmpty(Flux.error(throwsUp("Sorry, there's no ingredients")));
				
	}

		return manager.findById(id)
				.map(i -> ResponseEntity.ok(i))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	Mono<ResponseEntity<IngredientJson>> findIngredientById(String id) {
	}

	Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson newIngredientJson) {
		return Mono.just(newIngredientJson)
				.map(IngredientJson::toIngredient) 
				.flatMap(x -> manager.save(x))
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok);
	}
	
}
