package com.whattacook.view.service.recipe;

import org.springframework.http.ResponseEntity;

import com.whattacook.model.recipe.RecipeJson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeDetailService {
	
	//GET all
	Flux<RecipeJson> showAllRecipes();

	//GET by id
	Mono<ResponseEntity<RecipeJson>> showRecipeById(RecipeJson recipeJson);

	//POST
	Mono<ResponseEntity<RecipeJson>> saveNewRecipe(RecipeJson recipeJson);
	
	//PUT
	Mono<ResponseEntity<RecipeJson>> modifyRecipe(RecipeJson recipeJson);

	//DELETE
	Mono<ResponseEntity<Void>> deleteRecipe(RecipeJson recipeJson);

}
