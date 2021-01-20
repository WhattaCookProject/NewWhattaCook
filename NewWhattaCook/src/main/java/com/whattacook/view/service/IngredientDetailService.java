package com.whattacook.view.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngredientDetailService {

	//GET all
	public Flux<Ingredient> showAllIngredients();
	
	//GET by id
	public Mono<ResponseEntity<Ingredient>> showIngredientById(String id);
	
	//POST
	public Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson ingredient);
	
	//PUT
	public Mono<ResponseEntity<Ingredient>> modifyNameIngredient(IngredientJson ingredient);
	
	//DELETE
	public Mono<ResponseEntity<Void>> deleteIngredient(String id);

	//Returns HashMap with recipe id & times that is repeated
	Flux<ResponseEntity<HashMap<Integer, Object>>> recipeCounter(List<String> listIngredientId);

}
