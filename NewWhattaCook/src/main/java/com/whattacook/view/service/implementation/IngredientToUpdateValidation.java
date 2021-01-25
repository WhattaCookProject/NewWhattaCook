package com.whattacook.view.service.implementation;

import static com.whattacook.util.Valid.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.util.exceptions.IngredientExceptions;

class IngredientToUpdateValidation {
	
	static void verifyIsAble(IngredientJson ingredientJson) throws IngredientExceptions {

		validate(ingredientJson).ifNotHaveAllNeededToBeCreatedThrowException();

	}

	////// PRIVATE METHODS ////// ////// PRIVATE METHODS //////

	private static IngredientToUpdateValidation validate(IngredientJson ingredientJson) {
		return new IngredientToUpdateValidation(ingredientJson);
	}

	private void ifNotHaveAllNeededToBeCreatedThrowException() throws IngredientExceptions {
		if (notHaveAllNeededToBeCreated())
			throwExceptionWithEspecificFlawsOfThis();
	}

	private boolean notHaveAllNeededToBeCreated() {
		return isNullOrEmpty(ingredientJson.getName()) || isNullOrEmpty(ingredientJson.getId());
	}

	private Throwable throwExceptionWithEspecificFlawsOfThis() {

		List<String> message = new ArrayList<>();

		if (isNullOrEmpty(ingredientJson.getId()))
			message.add("Missing ID!");

		if (isNullOrEmpty(ingredientJson.getName()))
			message.add("Missing Name!");

		return IngredientExceptions.throwsUp(String.join(" ", message));
	}

	private IngredientToUpdateValidation(IngredientJson ingredientJson) {
		this.ingredientJson = ingredientJson;
	}

	private final IngredientJson ingredientJson;

}
