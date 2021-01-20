package com.whattacook.view.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.recipe.Recipe;
import com.whattacook.view.service.RecipeDetailService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecipeService implements RecipeDetailService {
	
	@Autowired
	private RecipeComponent component;

	@Override
	public Flux<Recipe> showAllRecipes() {
		
		Flux<Recipe> response = Flux.empty();
		
		try {
			
			response = component.findAllRecipe();
			
		} catch (Exception e) {
			response = Flux.error(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Recipe>> showRecipeById(Long id) {

		Mono<ResponseEntity<Recipe>> response = Mono.empty();
		
		try {
			
			
		} catch (Exception e) {
			response = Mono.error(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Recipe>> saveNewRecipe(Recipe recipe) {

		Mono<ResponseEntity<Recipe>> response = Mono.empty();
		
		try {
			
			
		} catch (Exception e) {
			response = Mono.error(e);
		}
		
		return response;
	}
	
	@Override
	public Mono<ResponseEntity<Recipe>> modifyRecipe(Recipe recipe) {

		Mono<ResponseEntity<Recipe>> response = Mono.empty();
		
		try {
			
			
		} catch (Exception e) {
			response = Mono.error(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteRecipe(Long id) {


		Mono<ResponseEntity<Void>> response = Mono.empty();
		
		try {
			
			
		} catch (Exception e) {
			response = Mono.error(e);
		}
		
		return response;
	}

}
