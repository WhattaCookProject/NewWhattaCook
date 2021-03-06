package com.whattacook.view.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.whattacook.model.ingredient.IngredientJson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngredientDetailService {

	//GET all
	public Flux<IngredientJson> showAllIngredients();
	
	//GET by id
	public Mono<ResponseEntity<IngredientJson>> showIngredient(IngredientJson ingredient);
	
	//POST
	public Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson ingredient);
	
	//PUT
	public Mono<ResponseEntity<IngredientJson>> modifyNameIngredient(IngredientJson ingredient);
	
	//DELETE
	public Mono<ResponseEntity<Void>> deleteIngredient(IngredientJson ingredient);

	//Returns HashMap with recipe id & times that is repeated
	Flux<ResponseEntity<HashMap<Integer, Object>>> recipeCounter(List<String> listIngredientId);

}
