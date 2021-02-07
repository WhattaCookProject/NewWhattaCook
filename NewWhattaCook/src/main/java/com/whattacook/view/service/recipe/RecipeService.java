package com.whattacook.view.service.recipe;

import static com.whattacook.model.recipe.RecipeJson.ERROR;
import static com.whattacook.view.service.Response.recipeError303;
import static com.whattacook.view.service.Response.voidError303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.recipe.RecipeJson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecipeService implements RecipeDetailService {
	
	@Autowired
	private RecipeServiceComponent component;

	@Override
	public Flux<RecipeJson> showAllRecipes() {
		
		Flux<RecipeJson> response = Flux.empty();
		
		try {
			
			response = component.findAllRecipe();
			
		} catch (Exception e) {
			response = Flux.just(ERROR(e.getMessage()));
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<RecipeJson>> showRecipeById(RecipeJson recipeJson) {

		Mono<ResponseEntity<RecipeJson>> response = Mono.empty();
		
		try {
			
			response = component.findRecipeById(recipeJson);
			
		} catch (Exception e) {
			response = recipeError303(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<RecipeJson>> saveNewRecipe(RecipeJson recipeJson) {

		Mono<ResponseEntity<RecipeJson>> response = Mono.empty();
		
		try {
			
			RecipeServiceValidation.toSave(recipeJson);
			
			component.ifTitleIsAlredyRegisteredThrowsException(recipeJson);
			
			response = component.saveNewRecipe(recipeJson);
			
		} catch (Exception e) {
			response = recipeError303(e);
		}
		
		return response;
	}
	
	@Override
	public Mono<ResponseEntity<RecipeJson>> modifyRecipe(RecipeJson recipeJson) {

		Mono<ResponseEntity<RecipeJson>> response = Mono.empty();
		
		try {
			
			RecipeServiceValidation.toUpdate(recipeJson);
			
			response = component.updateRecipe(recipeJson);
			
			
		} catch (Exception e) {
			response = recipeError303(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteRecipe(RecipeJson recipeJson) {


		Mono<ResponseEntity<Void>> response = Mono.empty();
		
		try {
			
			response = component.deleteRecipe(recipeJson);
			
		} catch (Exception e) {
			response = voidError303(e);
		}
		
		return response;
	}

}
