package com.whattacook.view.service.implementation;

import static com.whattacook.util.Valid.isNullOrEmpty;
import static com.whattacook.util.exceptions.IngredientExceptions.throwsUp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.whattacook.model.ingredient.IngredientJson;

import reactor.core.publisher.Mono;

class IngredientComponentAccessory {

	static boolean ifHasNameTrueIfHasIdFalseElseThrowsException(IngredientJson ingredient) {
		ifNotHasNameAndIdThrowException(ingredient);
		return isNullOrEmpty(ingredient.getId());
	}

	private static void ifNotHasNameAndIdThrowException(IngredientJson ingredient) {
		if (isNullOrEmpty(ingredient.getId()) && isNullOrEmpty(ingredient.getName()))
			throwsUp("Missing ID or Name to search!");
	}

	static ResponseEntity<IngredientJson> ResponseNotFound() {
		return ResponseEntity.notFound().header("ERROR", "Ingredient not founded!").build();
	}
	
	static ResponseEntity<IngredientJson> ResponseNotContent() {
		return ResponseEntity.noContent().header("ERROR", "Ingredient not founded!").build();
	}

}
