package com.whattacook.view.service.ingredient;

import static com.whattacook.util.Valid.isNullOrEmpty;
import static com.whattacook.util.exceptions.IngredientExceptions.throwsUp;
import static com.whattacook.view.service.Response.ingredienteError303;
import static com.whattacook.view.service.Response.ingredienteError303NotFound;
import static com.whattacook.view.service.Response.voidError303;
import static com.whattacook.view.service.Response.voidError303NotFound;

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
class IngredientServiceComponent {
	
	@Autowired
	private IngredientManager manager;
	
	Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson newIngredientJson) {
		return Mono.just(newIngredientJson)
				.map(IngredientJson::toIngredient) 
				.flatMap(manager::save)
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok)
				.onErrorReturn(ingredienteError303());
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
				.switchIfEmpty(Flux.error(throwsUp("Sorry, there's nothing to cook")));
				
	}
	
	Mono<ResponseEntity<IngredientJson>> findIngredientByJson(IngredientJson ingredient) {
		return Mono.just(ingredient)
				.flatMap(x -> findIngredient(x))
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ingredienteError303NotFound())
				.onErrorReturn(ingredienteError303());
				
	}
	
	private Mono<Ingredient> findIngredient(IngredientJson ingredient) {
		return ifHasNameTrueIfHasIdFalseElseThrowsException(ingredient) 
				? manager.findByNameIgnoreCase(ingredient.getName()) 
						: manager.findById(ingredient.getId());
	}

	private boolean ifHasNameTrueIfHasIdFalseElseThrowsException(IngredientJson ingredient) {
		ifNotHasNameAndIdThrowException(ingredient);
		return isNullOrEmpty(ingredient.getId());
	}

	private void ifNotHasNameAndIdThrowException(IngredientJson ingredient) {
		if (isNullOrEmpty(ingredient.getId()) && isNullOrEmpty(ingredient.getName()))
			throwsUp("Missing ID and Name to search!");
	}
	
	Mono<ResponseEntity<Void>> deleteIngredient(IngredientJson ingredient) {
		return Mono.just(ingredient.getId())
				.flatMap(manager::findById)
					.flatMap(manager::delete)
						.map(ResponseEntity::ok)
				.defaultIfEmpty(voidError303NotFound())
				.onErrorReturn(voidError303());
	}

	Mono<ResponseEntity<IngredientJson>> updateIngredient(IngredientJson ingredientJson) {
		return  Mono.just(ingredientJson)
				.flatMap(json -> manager.findById(json.getId()))
				.flatMap(ingredient -> {
					ingredient.setName(TitleCase.all(ingredientJson.getName()));
					return manager.save(ingredient);
				})
				.map(Ingredient::toJson)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ingredienteError303NotFound())
				.onErrorReturn(ingredienteError303());
	}
	
}
