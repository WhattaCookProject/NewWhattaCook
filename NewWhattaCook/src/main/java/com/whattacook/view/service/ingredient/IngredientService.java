package com.whattacook.view.service.ingredient;

import static com.whattacook.model.ingredient.IngredientJson.ERROR;
import static com.whattacook.view.service.Response.ingredienteError303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.view.service.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientService implements IngredientDetailService {
	
	@Autowired
	private IngredientServiceComponent component;

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
			response = ingredienteError303(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<IngredientJson>> saveNewIngredient(IngredientJson ingredientJson) {

		Mono<ResponseEntity<IngredientJson>> response = Mono.empty();
		
		try {
			
			IngredientServiceValidation.toSave(ingredientJson);
			
			component.ifNameIsAlredyRegisteredThrowsException(ingredientJson);
			
			response = component.saveNewIngredient(ingredientJson);
			
		} catch (Exception e) {
			response = ingredienteError303(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<IngredientJson>> modifyNameIngredient(IngredientJson ingredientJson) {


		Mono<ResponseEntity<IngredientJson>> response = Mono.empty();
		
		try {
			
			IngredientServiceValidation.toUpdate(ingredientJson);
			
			response = component.updateIngredient(ingredientJson);
			
			
		} catch (Exception e) {
			response = ingredienteError303(e);
		}
		
		return response;
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteIngredient(IngredientJson ingredientJson) {


		Mono<ResponseEntity<Void>> response = Mono.empty();
		
		try {
			
			response = component.deleteIngredient(ingredientJson);
			
		} catch (Exception e) {
			response = Response.voidError303(e);
		}
		
		return response;
	}

}
