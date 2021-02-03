package com.whattacook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.whattacook.model.recipe.RecipeJson;
import com.whattacook.view.service.implementation.RecipeService;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
@RequestMapping("/api/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService service;
	

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public Flux<RecipeJson> showAllRecipes() {
		return service.showAllRecipes();
	}

}
