package com.whattacook.view.service.implementation;

import static com.whattacook.model.ingredient.IngredientJson.ERROR;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.view.service.IngredientDetailService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientService implements IngredientDetailService {
	
	@Autowired
	private IngredientComponent component;

	@Override
	public Flux<IngredientJson> showAllIngredients() {

		Flux<IngredientJson> response = Flux.empty();
		
		try {

			response = component.findAllIngredients();
			
		} catch (Exception e) {
			response = Flux.just(ERROR(e.getMessage()));
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<IngredientJson>> showIngredient(IngredientJson ingredientJson) {

		Mono<ResponseEntity<IngredientJson>> response = Mono.empty();
		
		try {
			
			response = component.findIngredientByJson(ingredientJson);
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson ingredientJson) {

		Mono<ResponseEntity<IngredientJson>> response = Mono.empty();
		
		try {
			
			IngredientToSaveValidation.verifyIsAble(ingredientJson);
			
			component.ifNameIsAlredyRegisteredThrowsException(ingredientJson);
			
			response = component.saveNewIngredient(ingredientJson);
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<IngredientJson>> modifyNameIngredient(IngredientJson ingredientJson) {


		Mono<ResponseEntity<IngredientJson>> response = Mono.empty();
		
		try {
			
			IngredientToUpdateValidation.verifyIsAble(ingredientJson);
			
			response = component.updateIngredient(ingredientJson);
			
			
		} catch (Exception e) {
			response = Mono.just(ResponseEntity.status(303)
					.header("ERROR", e.getMessage()).build());
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteIngredient(IngredientJson ingredientJson) {


		Mono<ResponseEntity<Void>> response = Mono.empty();
		
		try {
			
			response = component.deleteIngredient(ingredientJson);
			
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
