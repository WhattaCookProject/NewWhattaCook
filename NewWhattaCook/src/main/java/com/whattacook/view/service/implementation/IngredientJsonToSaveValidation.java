package com.whattacook.view.service.implementation;

import static com.whattacook.util.Valid.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.util.exceptions.IngredientExceptions;

public class IngredientJsonToSaveValidation {

	static void verifyIsAble(IngredientJson ingredientJson) throws IngredientExceptions {

		valid(ingredientJson).ifNotHaveAllNeededToBeCreatedThrowException();

	}
	
	////// PRIVATE METHODS //////	////// PRIVATE METHODS //////	////// PRIVATE METHODS //////

	private static IngredientJsonToSaveValidation valid(IngredientJson ingredientJson) {
		return new IngredientJsonToSaveValidation(ingredientJson);
	}

	private void ifNotHaveAllNeededToBeCreatedThrowException() throws IngredientExceptions {
		if (notHaveAllNeededToBeCreated())
			throwExceptionWithEspecificFlawsOfThis();
	}

	private boolean notHaveAllNeededToBeCreated() {
		return isNullOrEmpty(ingredientJson.getName());
	}

	private Throwable throwExceptionWithEspecificFlawsOfThis() {

		List<String> message = new ArrayList<>();

		if (isNullOrEmpty(ingredientJson.getName()))
			message.add("Missing Name!");

		return IngredientExceptions.throwsUp(String.join(" ", message));
	}
	
	private IngredientJsonToSaveValidation(IngredientJson ingredientJson) {
		this.ingredientJson = ingredientJson;
	}

	private final IngredientJson ingredientJson;

}
