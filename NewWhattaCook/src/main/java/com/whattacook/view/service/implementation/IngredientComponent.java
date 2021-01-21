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
				.map(Ingredient::toJson)
				.switchIfEmpty(Flux.error(throwsUp("Sorry, there's no ingredients")));
				
	}

	Mono<ResponseEntity<IngredientJson>> findIngredientById(String id) {
		return  Mono.just(id)
				.flatMap(manager::findById)
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.noContent()
						.header("ERROR", "Ingredient not founded!").build());
	}

	Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson newIngredientJson) {
		return Mono.just(newIngredientJson)
				.map(IngredientJson::toIngredient) 
				.flatMap(manager::save)
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok);
	}
	
}
