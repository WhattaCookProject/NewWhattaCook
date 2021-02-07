package com.whattacook.view.service;

import org.springframework.http.ResponseEntity;

import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.model.recipe.RecipeJson;

import reactor.core.publisher.Mono;

public class Response {
	
	// INGREDIENT RESPONSES
	
	public static ResponseEntity<IngredientJson> ingredienteError303() {
		return ResponseEntity.status(303).header("ERROR", GENERAL_ERROR).build();
	}

	public static ResponseEntity<IngredientJson> ingredienteError303(String msg) {
		return ResponseEntity.status(303).header("ERROR", msg).build();
	}
	
	public static ResponseEntity<IngredientJson> ingredienteError303NotFound() {
		return ResponseEntity.status(303).header("ERROR", INGREDIET_NOT_FOUND).build();
	}
	
	public static Mono<ResponseEntity<IngredientJson>> ingredienteError303(Exception e) {
		return Mono.just(ResponseEntity.status(303).header("ERROR", e.getMessage()).build());
	}
	
	// RECIPE RESPONSES
	
	public static ResponseEntity<RecipeJson> recipeError303() {
		return ResponseEntity.status(303).header("ERROR", GENERAL_ERROR).build();
	}
	
	public static ResponseEntity<RecipeJson> recipeError303(String msg) {
		return ResponseEntity.status(303).header("ERROR", msg).build();
	}
	
	public static ResponseEntity<RecipeJson> recipeError303NotFound() {
		return ResponseEntity.status(303).header("ERROR", RECIPE_NOT_FOUND).build();
	}
	
	public static Mono<ResponseEntity<RecipeJson>> recipeError303(Exception e) {
		return Mono.just(ResponseEntity.status(303).header("ERROR", e.getMessage()).build());
	}
	
	// VOID RESPONSES
	
	public static ResponseEntity<Void> voidError303NotFound() {
		return ResponseEntity.status(303).header("ERROR", "Not Found!").build();
	}
	
	public static ResponseEntity<Void> voidError303() {
		return ResponseEntity.status(303).header("ERROR", GENERAL_ERROR).build();
	}

	public static ResponseEntity<Void> voidError303(String msg) {
		return ResponseEntity.status(303).header("ERROR", msg).build();
	}
	
	public static Mono<ResponseEntity<Void>> voidError303(Exception e) {
		return Mono.just(ResponseEntity.status(303).header("ERROR", e.getMessage()).build());
	}
	
	// FINAL VARIABLES
	
	private static final String INGREDIET_NOT_FOUND = "Ingredient not founded!";
	private static final String RECIPE_NOT_FOUND = "Recipe not founded!";
	private static final String GENERAL_ERROR = "Something went wrong!";

}
