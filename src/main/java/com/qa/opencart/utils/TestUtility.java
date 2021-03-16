package com.qa.opencart.utils;

import java.util.Random;

public class TestUtility {
	
	public static String getRandomEmail() {
		Random numgenerator = new Random();
		String email = "testautone"+ numgenerator.nextInt(5000)+"@gmail.com";
		return email;
	}
}
