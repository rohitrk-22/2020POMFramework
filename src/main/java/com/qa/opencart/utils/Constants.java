package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String ACCOUNT_PAGE_TITLE ="My Account";
	public static final int ACCOUNT_PAGE_SECTIONS =5;
	public static final int PRODUCT_IMAGES_COUNT_FOR_MACBOOK =4;
	public static final String SHOPPING_CART_PAGE_TITLE="Shopping Cart";
	public static final String ACCOUNT_CREATION_SUCCESS_MSG="Your Account Has Been Created!";
	
	public static List<String> expectedAccSectionList() {
		List<String> exAccSecList= new ArrayList<String>();
		exAccSecList.add("My Account");
		exAccSecList.add("My Orders");
		exAccSecList.add("My Affiliate Account");
		exAccSecList.add("Newsletter");
		return exAccSecList;
	}
}
