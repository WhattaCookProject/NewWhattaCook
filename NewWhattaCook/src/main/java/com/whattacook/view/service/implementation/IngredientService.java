package com.whattacook.view.service.implementation;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.view.service.IngredientDetailService;

@Service
public class IngredientService implements IngredientDetailService {
	
	@Autowired
	private IngredientComponent component;

	@Override
	public ResponseEntity<List<Ingredient>> showAllIngredients() {

		ResponseEntity<List<Ingredient>> response = ResponseEntity.noContent().build();
		
		try {
			
			List<Ingredient> listRecipe = component.findAllIngredients();
			
			response = ResponseEntity.ok(listRecipe);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<Ingredient> showIngredientById(Long id) {

		ResponseEntity<Ingredient> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.of(null);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<Ingredient> saveNewIngredient(Ingredient ingredient) {

		ResponseEntity<Ingredient> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.of(null);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<Ingredient> modifyNameIngredient(Ingredient ingredient) {

		ResponseEntity<Ingredient> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.of(null);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<Void> deleteIngredient(Long id) {

		ResponseEntity<Void> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.ok().build();
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<HashMap<Integer, Object>> recipeCounter(List<String> listIngredientId) {

		ResponseEntity<HashMap<Integer, Object>> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.of(null);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

}
