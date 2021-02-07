package com.whattacook.view.service.ingredient;

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

}
