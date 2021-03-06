package com.whattacook.util;

import org.springframework.util.StringUtils;

public class Valid {

	private Valid() {
		throw new RuntimeException("It is forbidden to create instances of this utilitarian class");
	}
	
	/**
	 * "Something went wrong trying to {@code input} : "
	 * 
	 * @param input string to complete the message
	 * @return string complete message
	 */
	public static String msgError(String input) {
		return String.format("Something went wrong trying to %s : ", input);
	}

	public static boolean isNullOrEmpty(String something) {
		return !StringUtils.hasLength(something);
	}
	
	public static boolean isNullOrLessThanOne(Number something) {
		return something == null || something.longValue() <= 0;
	}
	
	public static boolean notNullOrEmpty(String something) {
		return StringUtils.hasLength(something);
	}
	
	public static boolean notNullOrLessThanOne(Number something) {
		return something != null && something.longValue() > 0;
	}
	

}
