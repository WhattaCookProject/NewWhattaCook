package com.whattacook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.whattacook.model.recipe.RecipeJson;
import com.whattacook.view.service.RecipeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService service;
	

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public Flux<RecipeJson> showAllRecipes() {
		return service.showAllRecipes();
	}
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public Mono<ResponseEntity<RecipeJson>> showRecipe(@RequestBody RecipeJson recipeJson) {
		return service.showRecipeById(recipeJson);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ResponseEntity<RecipeJson>> saveNewRecipe(@RequestBody RecipeJson recipeJson) {
		return service.saveNewRecipe(recipeJson);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Mono<ResponseEntity<RecipeJson>> modifyRecipe(@RequestBody RecipeJson recipeJson) {
		return service.modifyRecipe(recipeJson);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public Mono<ResponseEntity<Void>> deleteRecipe(@RequestBody RecipeJson recipeJson) {
		return service.deleteRecipe(recipeJson);
	}

}
