package com.whattacook.view.service.implementation;

import static com.whattacook.util.exceptions.IngredientExceptions.throwsUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return Flux.from(manager.findAll())
				.map(Ingredient::toJson)
				.defaultIfEmpty(IngredientJson.error("Sorry, there's nothing to cook"));
				
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
		ifNameIsAlredyRegisteredThrowsException(newIngredientJson);
		return Mono.just(newIngredientJson)
				.map(IngredientJson::toIngredient) 
				.flatMap(manager::save)
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok);
	}

	private void ifNameIsAlredyRegisteredThrowsException(IngredientJson newIngredientJson) {
		if (nameIsAlredyRegistered(newIngredientJson))
			throwsUp("This Ingredient is already registered!");
	}
	
	private boolean nameIsAlredyRegistered(IngredientJson newIngredientJson) {
		return manager.existsByNameIgnoreCase(newIngredientJson.getName()).block();
	}

	Mono<ResponseEntity<Void>> deleteIngredient(String id) {
		return Mono.just(id)
				.flatMap(manager::findById)
				.flatMap(x -> manager.delete(x)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(ResponseEntity.notFound()
						.header("ERROR", "Ingredient not founded!").build());
	}
	
}
