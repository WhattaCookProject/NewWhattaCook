package com.whattacook.view.service.implementation;

import static com.whattacook.util.exceptions.IngredientExceptions.throwsUp;
import static com.whattacook.view.service.implementation.IngredientComponentAccessory.ResponseNotFound;
import static com.whattacook.view.service.implementation.IngredientComponentAccessory.ResponseNotContent;
import static com.whattacook.view.service.implementation.IngredientComponentAccessory.ResponseVoidNotFound;
import static com.whattacook.view.service.implementation.IngredientComponentAccessory.ResponseVoidOk;
import static com.whattacook.view.service.implementation.IngredientComponentAccessory.ifHasNameTrueIfHasIdFalseElseThrowsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.model.ingredient.IngredientManager;
import com.whattacook.util.TitleCase;
import com.whattacook.util.exceptions.IngredientExceptions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
class IngredientComponent {
	
	@Autowired
	private IngredientManager manager;
	
	Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson newIngredientJson) {
		return Mono.just(newIngredientJson)
				.map(IngredientJson::toIngredient) 
				.flatMap(manager::save)
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok);
	}
	
	void ifNameIsAlredyRegisteredThrowsException(IngredientJson newIngredientJson) {
		if (nameIsAlredyRegistered(newIngredientJson.getName()))
			throwsUp("This Ingredient is already registered!");
	}
	
	boolean nameIsAlredyRegistered(String name) {
		return manager.existsByNameIgnoreCase(name).block();
	}

	Flux<IngredientJson> findAllIngredients() throws IngredientExceptions {
		return Flux.from(manager.findAll())
				.map(Ingredient::toJson)
				.defaultIfEmpty(IngredientJson.error("Sorry, there's nothing to cook"));
				
	}
	
	Mono<ResponseEntity<IngredientJson>> findIngredientByJson(IngredientJson ingredient) {
		return Mono.just(ingredient)
				.flatMap(x -> findIngredient(x))
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseNotContent())
				.onErrorReturn(ResponseNotFound());
				
	}
	
	private Mono<Ingredient> findIngredient(IngredientJson ingredient) {
		return ifHasNameTrueIfHasIdFalseElseThrowsException(ingredient) 
				? manager.findByNameIgnoreCase(ingredient.getName()) 
						: manager.findById(ingredient.getId());
	}
	
	Mono<ResponseEntity<Void>> deleteIngredient(IngredientJson ingredient) {
		return Mono.just(ingredient)
				.flatMap(x -> findIngredient(x))
				.flatMap(x -> manager.delete(x).then(ResponseVoidOk()))
				.defaultIfEmpty(ResponseVoidNotFound())
				.onErrorReturn(ResponseVoidNotFound());
	}

	Mono<ResponseEntity<IngredientJson>> updateIngredient(IngredientJson ingredientJson) {
		return  Mono.just(ingredientJson)
				.flatMap(x -> findIngredient(x))
				.flatMap(x -> {
					x.setName(TitleCase.all(ingredientJson.getName()));
					return manager.save(x);
				})
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseNotContent())
				.onErrorReturn(ResponseNotFound());
	}
	
}
