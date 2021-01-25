package com.whattacook.util;

import java.util.HashMap;

import org.springframework.lang.Nullable;
import static com.whattacook.util.Valid.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"success", "message"})
public class Response {
	
	public static Response error(String message) {
		Response response = new Response();
		response.put(SUCCESS, false);
		response.put(MESSAGE, setMessage(message));
		return response;
	}
	
	public static Response success(String message, @Nullable Object content) {
		Response response = new Response();
		response.put(SUCCESS, true);
		response.put(MESSAGE, setMessage(message));
		if (content != null)
			response.put(CONTENT, content);
		return response;
	}
	
	public static String setMessage(String message) {
		return (notNullOrEmpty(message)) ? message : MESSAGE_ERROR;
	}
	
	public String getMessage() {
		return (get(MESSAGE) == null) ? MESSAGE_ERROR : (String) get(MESSAGE);
	}
	
	public boolean isSuccess() {
		return (boolean) get(SUCCESS);
	}
	
	public Object getContent() {
		return (map.containsKey(CONTENT)) ? get(CONTENT) : CONTENT_ERROR;
	}

	@Override
	public String toString() {
		return "Response : {"
				+ "success : " + isSuccess() 
				+ ", message : " + getMessage() 
				+ ", content : " + getContent() 
				+ "}";
	}

	private Response() {
		map = new HashMap<String, Object>();
	}
	
	private void put(String key, Object value) {
		map.put(key, value);
	}
	
	private Object get(String key) {
		return map.get(key);
	}
	
	private final HashMap<String, Object> map;
	private final String CONTENT_ERROR = "This Response have no content!";
	private final static String MESSAGE_ERROR = "Sorry, this Response has no message!";
	private final static String SUCCESS = "success";
	private final static String MESSAGE = "message";
	private final static String CONTENT = "content";

}
