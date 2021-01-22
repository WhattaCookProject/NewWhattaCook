package com.whattacook.model.recipe;

public class RecipeJson {
	
	private String id;
	private String title;
	private String instructions;
	
	
	public RecipeJson() {
	}

	public RecipeJson(String id, String title, String instructions) {
		this.id = id;
		this.title = title;
		this.instructions = instructions;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
}
