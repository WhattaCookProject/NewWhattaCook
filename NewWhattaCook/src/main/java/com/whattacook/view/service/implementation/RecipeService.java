package com.whattacook.view.service.implementation;

import static com.whattacook.model.recipe.RecipeJson.ERROR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.recipe.RecipeJson;
import com.whattacook.view.service.RecipeDetailService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecipeService implements RecipeDetailService {
	
	@Autowired
	private RecipeComponent component;

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
			
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<RecipeJson>> saveNewRecipe(RecipeJson recipeJson) {

		Mono<ResponseEntity<RecipeJson>> response = Mono.empty();
		
		try {
			
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}
	
	@Override
	public Mono<ResponseEntity<RecipeJson>> modifyRecipe(RecipeJson recipeJson) {

		Mono<ResponseEntity<RecipeJson>> response = Mono.empty();
		
		try {
			
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteRecipe(RecipeJson recipeJson) {


		Mono<ResponseEntity<Void>> response = Mono.empty();
		
		try {
			
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

}
