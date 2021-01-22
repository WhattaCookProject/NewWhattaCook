package com.whattacook.view.service.implementation;

import static com.whattacook.util.Valid.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.util.exceptions.IngredientExceptions;


public class IngredientJsonToSaveValidation {
	
	static void verifyIsAble(IngredientJson ingredientJson) throws IngredientExceptions {
	IngredientJsonToSaveValidation verify = new IngredientJsonToSaveValidation(ingredientJson);
	
	verify.ifNotHaveAllNeededToBeCreatedThrowException();
	
}

private IngredientJsonToSaveValidation(IngredientJson ingredientJson) {
	this.ingredientJson = ingredientJson;
}

private void ifNotHaveAllNeededToBeCreatedThrowException() throws IngredientExceptions {
	if (notHaveAllNeededToBeCreated())
		throwExceptionWithEspecificFlawsOfThis();
}

private boolean notHaveAllNeededToBeCreated() {
	return isNullOrEmpty(ingredientJson.getId()) || isNullOrEmpty(ingredientJson.getName()) 
			|| isNullOrEmpty(ingredientJson.getId()) || isNullOrEmpty(ingredientJson.getName());
}

private void throwExceptionWithEspecificFlawsOfThis() throws IngredientExceptions {
	
	List<String> message = new ArrayList<>();

	if (isNullOrEmpty(ingredientJson.getId()))
		message.add("Missing Id!");

	if (isNullOrEmpty(ingredientJson.getName()))
		message.add("Missing Name!");

	IngredientExceptions.throwsUp(String.join(" ", message));
}

private final IngredientJson ingredientJson;


}
