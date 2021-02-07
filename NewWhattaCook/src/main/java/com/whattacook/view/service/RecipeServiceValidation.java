package com.whattacook.view.service;

import static com.whattacook.util.Valid.isNullOrEmpty;
import static com.whattacook.util.Valid.notNullOrEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.model.recipe.Recipe;
import com.whattacook.model.recipe.RecipeJson;
import com.whattacook.util.exceptions.IngredientExceptions;
import com.whattacook.util.exceptions.RecipeExceptions;

class RecipeServiceValidation {
	
	static void toSave(RecipeJson recipeJson) throws RecipeExceptions {
		SaveValidation.verifyIsAble(recipeJson);
	}

	public static void toUpdate(RecipeJson recipeJson) throws RecipeExceptions {
		UpdateValidation.verifyIsAble(recipeJson);
	}
	
	public static Recipe updateByJson(Recipe recipe, RecipeJson recipeJson) {
		return UpdateRecipe.byJson(recipe, recipeJson);
	}

	private static class SaveValidation {

		public static void verifyIsAble(RecipeJson recipeJson) throws RecipeExceptions {
			validate(recipeJson).ifNotHaveAllNeededToBeCreatedThrowException();
		}
		
		// PRIVATE METHODS // PRIVATE METHODS //

		private static SaveValidation validate(RecipeJson recipeJson) {
			return new SaveValidation(recipeJson);
		}
		
		private void ifNotHaveAllNeededToBeCreatedThrowException() throws RecipeExceptions {
			if (notHaveAllNeededToBeCreated())
				throwExceptionWithEspecificFlawsOfThis();
		}

		private boolean notHaveAllNeededToBeCreated() {
			return notNullOrEmpty(recipeJson.getId()) || isNullOrEmpty(recipeJson.getTitle())
					|| isNullOrEmpty(recipeJson.getInstructions()) || isNullOrEmpty(recipeJson.getIngredients());
		}

		private Throwable throwExceptionWithEspecificFlawsOfThis() {

			List<String> message = new ArrayList<>();

			if (notNullOrEmpty(recipeJson.getId()))
				message.add("Must not have ID!");

			if (isNullOrEmpty(recipeJson.getTitle()))
				message.add("Missing Name!");
			
			if (isNullOrEmpty(recipeJson.getInstructions()))
				message.add("Missing Instructions!");
			
			if (isNullOrEmpty(recipeJson.getIngredients()))
				message.add("Missing Ingredients!");

			return IngredientExceptions.throwsUp(String.join(" ", message));
		}

		private SaveValidation(RecipeJson recipeJson) {
			this.recipeJson = recipeJson;
		}
		
		private final RecipeJson recipeJson;
		
	}

	private static class UpdateValidation {


		public static void verifyIsAble(RecipeJson recipeJson) {
			verify(recipeJson).ifNotHaveAllNeededToBeCreatedThrowException();
		}
		
		// PRIVATE METHODS // PRIVATE METHODS //

		private static UpdateValidation verify(RecipeJson recipeJson) {
			return new UpdateValidation(recipeJson);
		}

		private void ifNotHaveAllNeededToBeCreatedThrowException() throws RecipeExceptions {
			if (notHaveAllNeededToBeCreated())
				throwExceptionWithEspecificFlawsOfThis();
		}

		private boolean notHaveAllNeededToBeCreated() {
			return isNullOrEmpty(recipeJson.getId()) || (isNullOrEmpty(recipeJson.getTitle())
					&& isNullOrEmpty(recipeJson.getInstructions()) && isNullOrEmpty(recipeJson.getIngredients()));
		}

		private Throwable throwExceptionWithEspecificFlawsOfThis() {

			List<String> message = new ArrayList<>();

			if (isNullOrEmpty(recipeJson.getId()))
				message.add("Missing ID!");

			if (isNullOrEmpty(recipeJson.getTitle()) && isNullOrEmpty(recipeJson.getInstructions())
					&& isNullOrEmpty(recipeJson.getIngredients())) {
				message.add("Missing Name OR Instructions OR Ingredients!");
			}

			return IngredientExceptions.throwsUp(String.join(" ", message));
		}
		
		private UpdateValidation(RecipeJson recipeJson) {
			this.recipeJson = recipeJson;
		}
		
		private final RecipeJson recipeJson;
		
	}
	
	private static class UpdateRecipe {
		
		public static Recipe byJson(Recipe recipe, RecipeJson recipeJson) {
			return update(recipe).by(recipeJson);
		}

		private Recipe by(RecipeJson recipeJson) {
			if (notNullOrEmpty(recipeJson.getTitle()) 
					&& !recipe.getTitle().equals(recipeJson.getTitle()))
				recipe.setTitle(recipeJson.getTitle());
			
			if (notNullOrEmpty(recipeJson.getInstructions()) 
					&& !recipe.getInstructions().equals(recipeJson.getInstructions()))
				recipe.setInstructions(recipeJson.getInstructions());
			
			if (notNullOrEmpty(recipeJson.getIngredients())) {
				SortedSet<Ingredient> ingredientsNew = recipeJson.getIngredients().stream()
						.map(IngredientJson::toIngredient)
						.collect(Collectors.toCollection(() -> new TreeSet<>()));
				
				if (!ingredientsNew.equals(recipe.getIngredients()))
					recipe.setIngredients(ingredientsNew);
			}

			return recipe;
		}

		private static UpdateRecipe update(Recipe recipe) {
			return new UpdateRecipe(recipe);
		}

		private UpdateRecipe(Recipe recipe) {
			this.recipe = recipe;
		}
		
		private final Recipe recipe;
	}
	
}
