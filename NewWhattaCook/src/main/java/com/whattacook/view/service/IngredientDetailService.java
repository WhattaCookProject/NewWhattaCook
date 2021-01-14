package com.whattacook.view.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.whattacook.model.ingredient.Ingredient;

public interface IngredientDetailService {

	//GET all
	public ResponseEntity<List<Ingredient>> showAllIngredients();
	
	//GET by id
	public ResponseEntity<Ingredient> showIngredientById(Long id);
	
	//POST
	public ResponseEntity<Ingredient> saveNewIngredient(Ingredient ingredient);
	
	//PUT
	public ResponseEntity<Ingredient> modifyNameIngredient(Ingredient ingredient);
	
	//DELETE
	public ResponseEntity<Void> deleteIngredient(Long id);

	//Returns HashMap with recipe id & times that is repeated
	ResponseEntity<HashMap<Integer, Object>> recipeCounter(List<String> listIngredientId);

}
