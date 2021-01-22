package com.whattacook.view.service.implementation;

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
		return Mono.just(newIngredientJson)
				.map(IngredientJson::toIngredient) 
				.flatMap(manager::save)
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok);
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
