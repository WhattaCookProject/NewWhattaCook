package com.whattacook.view.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.whattacook.model.recipe.Recipe;

public interface RecipeDetailService {
	
	//GET all
	ResponseEntity<List<Recipe>> showAllRecipes();

	//GET by id
	ResponseEntity<Recipe> showRecipeById(Long id);

	//POST
	ResponseEntity<Recipe> saveNewRecipe(Recipe recipe);
	
	//PUT
	ResponseEntity<Recipe> modifyRecipe(Recipe recipe);

	//DELETE
	ResponseEntity<Void> deleteRecipe(Long id);

}
