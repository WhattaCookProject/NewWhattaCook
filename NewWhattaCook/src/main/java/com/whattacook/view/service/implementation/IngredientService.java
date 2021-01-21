package com.whattacook.view.service.implementation;

import static com.whattacook.util.exceptions.IngredientExceptions.throwsUp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.view.service.IngredientDetailService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientService implements IngredientDetailService {
	
	@Autowired
	private IngredientComponent component;

	@Override
	public Flux<Ingredient> showAllIngredients() {

		Flux<Ingredient> response = Flux.empty();
		
		try {
			
			response = component.findAllIngredients();
			
		} catch (Exception e) {
			response = Flux.empty();
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Ingredient>> showIngredientById(String id) {

		Mono<ResponseEntity<Ingredient>> response = Mono.empty();
		
		try {
			
			throwsUp("Sorry, there's no ingredients");
			response = component.findIngredientById(id);
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson newIngredientJson) {

		Mono<ResponseEntity<IngredientJson>> response = Mono.empty();
		
		try {
			
			response = component.saveNewIngredient(newIngredientJson);

		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Ingredient>> modifyNameIngredient(IngredientJson ingredient) {


		Mono<ResponseEntity<Ingredient>> response = Mono.empty();
		
		try {
			
			
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteIngredient(String id) {


		Mono<ResponseEntity<Void>> response = Mono.empty();
		
		try {
			
			
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Flux<ResponseEntity<HashMap<Integer, Object>>> recipeCounter(List<String> listIngredientId) {

		Flux<ResponseEntity<HashMap<Integer, Object>>> response = Flux.empty();
		
		try {
			
			
			
		} catch (Exception e) {
			response = Flux.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

}
