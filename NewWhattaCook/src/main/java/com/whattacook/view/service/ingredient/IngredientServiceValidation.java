package com.whattacook.view.service.ingredient;

import static com.whattacook.util.Valid.isNullOrEmpty;
import static com.whattacook.util.Valid.notNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.util.exceptions.IngredientExceptions;

class IngredientServiceValidation {
	
	static void toSave(IngredientJson ingredientJson) throws IngredientExceptions {
		SaveValidation.verifyIsAble(ingredientJson);
	}
	
	static void toUpdate(IngredientJson ingredientJson) throws IngredientExceptions {
		UpdateValidation.verifyIsAble(ingredientJson);
	}
	
	////// PRIVATE CLASSES ////// ////// PRIVATE CLASSES //////	

	private static class SaveValidation {
		
		static void verifyIsAble(IngredientJson ingredientJson) throws IngredientExceptions {

			validate(ingredientJson).ifNotHaveAllNeededToBeCreatedThrowException();

		}

		////// PRIVATE METHODS ////// ////// PRIVATE METHODS //////

		private static SaveValidation validate(IngredientJson ingredientJson) {
			return new SaveValidation(ingredientJson);
		}

		private void ifNotHaveAllNeededToBeCreatedThrowException() throws IngredientExceptions {
			if (notHaveAllNeededToBeCreated())
				throwExceptionWithEspecificFlawsOfThis();
		}

		private boolean notHaveAllNeededToBeCreated() {
			return isNullOrEmpty(ingredientJson.getName()) || notNullOrEmpty(ingredientJson.getId());
		}

		private Throwable throwExceptionWithEspecificFlawsOfThis() {

			List<String> message = new ArrayList<>();

			if (notNullOrEmpty(ingredientJson.getId()))
				message.add("Must not have ID!");

			if (isNullOrEmpty(ingredientJson.getName()))
				message.add("Missing Name!");

			return IngredientExceptions.throwsUp(String.join(" ", message));
		}

		private SaveValidation(IngredientJson ingredientJson) {
			this.ingredientJson = ingredientJson;
		}

		private final IngredientJson ingredientJson;
		
	}
	
	private static class UpdateValidation {
		
		static void verifyIsAble(IngredientJson ingredientJson) throws IngredientExceptions {

			validate(ingredientJson).ifNotHaveAllNeededToBeCreatedThrowException();

		}

		////// PRIVATE METHODS ////// ////// PRIVATE METHODS //////

		private static UpdateValidation validate(IngredientJson ingredientJson) {
			return new UpdateValidation(ingredientJson);
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

		private UpdateValidation(IngredientJson ingredientJson) {
			this.ingredientJson = ingredientJson;
		}

		private final IngredientJson ingredientJson;

	}

}
