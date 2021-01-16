package com.whattacook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.whattacook.model.ingredient.Ingredient;
import com.whattacook.model.ingredient.IngredientJson;
import com.whattacook.view.service.implementation.IngredientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api/ingredient")
public class IngredientController {
	
	@Autowired
	private IngredientService service;
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Ingredient> showAllIngredients() {
		return service.showAllIngredients();
	}
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public Mono<ResponseEntity<Ingredient>> showIngredientById(@RequestBody @NonNull String id) {
		return service.showIngredientById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ResponseEntity<Ingredient>> saveNewIngredient(@RequestBody IngredientJson ingredient) {
		return service.saveNewIngredient(ingredient);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Mono<ResponseEntity<Ingredient>> modifyNameIngredient(@RequestBody IngredientJson ingredient) {
		return service.modifyNameIngredient(ingredient);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<ResponseEntity<Void>> deleteIngredient(@RequestBody @NonNull String id) {
		return service.deleteIngredient(id);
	}

}
