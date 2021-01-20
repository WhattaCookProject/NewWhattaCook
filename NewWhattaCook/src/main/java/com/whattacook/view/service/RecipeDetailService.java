package com.whattacook.view.service;

import org.springframework.http.ResponseEntity;

import com.whattacook.model.recipe.Recipe;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeDetailService {
	
	//GET all
	Flux<Recipe> showAllRecipes();

	//GET by id
	Mono<ResponseEntity<Recipe>> showRecipeById(Long id);

	//POST
	Mono<ResponseEntity<Recipe>> saveNewRecipe(Recipe recipe);
	
	//PUT
	Mono<ResponseEntity<Recipe>> modifyRecipe(Recipe recipe);

	//DELETE
	Mono<ResponseEntity<Void>> deleteRecipe(Long id);

}
