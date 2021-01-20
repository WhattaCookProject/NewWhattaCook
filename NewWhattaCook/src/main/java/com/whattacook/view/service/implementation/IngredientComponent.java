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

	Flux<Ingredient> findAllIngredients() throws IngredientExceptions {
		return manager.findAll()
				.switchIfEmpty(Flux.error(throwsUp("Sorry, there's no ingredients")));
				
	}

	Mono<ResponseEntity<Ingredient>> findIngredientById(String id) {
		return manager.findById(id)
				.map(i -> ResponseEntity.ok(i))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	public IngredientJson saveNewIngredient(IngredientJson newIngredientJson) {
		Ingredient ingredientToSave = newIngredientJson.toIngredient();
		Ingredient ingredientSaved = manager.save(ingredientToSave);
		return ingredientSaved.toJson();
	}
	
}
