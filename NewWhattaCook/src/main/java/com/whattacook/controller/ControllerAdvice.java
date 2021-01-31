package com.whattacook.controller;

import java.util.NoSuchElementException;

import javax.servlet.ServletException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.whattacook.util.exceptions.IngredientExceptions;
import com.whattacook.util.exceptions.RecipeExceptions;

@EnableWebMvc
@RestControllerAdvice
public class ControllerAdvice extends DefaultResponseErrorHandler {

	@ExceptionHandler(ServletException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String requestNotFound() {
		return "[NOT FOUND . 404] -> SORRY BABY!!!";
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String invalidRequest() {
		return "[BAD REQUEST . 400] -> Invalid or incorrect resquisition!!!";
	}
	
	@ExceptionHandler(IngredientExceptions.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String ingredientRequest() {
		return "Sorry, someone forgot the shopping list in the fridge";
	}
	
	@ExceptionHandler(RecipeExceptions.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String recipeRequest() {
		return "Sorry, we believe that the recipes are being prepared";
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String cantFoundWhatYouWant() {
		return "[INTERNAL SERVER ERROR . 500] -> Couldn't find what you want!!!";
	}

}
