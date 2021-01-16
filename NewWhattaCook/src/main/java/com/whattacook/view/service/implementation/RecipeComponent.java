package com.whattacook.view.service.implementation;

import static com.whattacook.util.exceptions.RecipeExceptions.throwsUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whattacook.model.recipe.Recipe;
import com.whattacook.model.recipe.RecipeManager;

import reactor.core.publisher.Flux;

@Component
class RecipeComponent {
	
	@Autowired
	private RecipeManager manager;

	Flux<Recipe> findAllRecipe() {
		return manager.findAll()
				.switchIfEmpty(Flux.error(throwsUp("Sorry, there's nothing to cook")));
	}

}
