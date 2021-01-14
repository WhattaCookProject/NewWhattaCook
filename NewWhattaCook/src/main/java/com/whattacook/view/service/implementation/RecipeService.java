package com.whattacook.view.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whattacook.model.recipe.Recipe;
import com.whattacook.view.service.RecipeDetailService;

@Service
public class RecipeService implements RecipeDetailService {
	
	@Autowired
	private RecipeComponent component;

	@Override
	public ResponseEntity<List<Recipe>> showAllRecipes() {
		
		ResponseEntity<List<Recipe>> response = ResponseEntity.noContent().build();
		
		try {
			
			List<Recipe> listRecipe = component.findAllRecipe();
			
			response = ResponseEntity.ok(listRecipe);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<Recipe> showRecipeById(Long id) {

		ResponseEntity<Recipe> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.of(null);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<Recipe> saveNewRecipe(Recipe recipe) {

		ResponseEntity<Recipe> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.of(null);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}
	
	@Override
	public ResponseEntity<Recipe> modifyRecipe(Recipe recipe) {
		
		ResponseEntity<Recipe> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.of(null);
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

	@Override
	public ResponseEntity<Void> deleteRecipe(Long id) {

		ResponseEntity<Void> response = ResponseEntity.noContent().build();
		
		try {
			
			
			response = ResponseEntity.ok().build();
			
		} catch (Exception e) {
			response = ResponseEntity.badRequest().header("Error", e.getMessage()).build();
		}
		
		return response;
	}

}
