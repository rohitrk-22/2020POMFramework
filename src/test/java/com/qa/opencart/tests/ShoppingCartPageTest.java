package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Story("US-106: Design ShoppingCart page for demcocart app with final price")
@Epic("US-105:Design ShoppingCart page fearure")
public class ShoppingCartPageTest extends BaseTest
{
	SoftAssert softAssert = new SoftAssert();
	@BeforeClass
	public void pageSetUp() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		searchResultPage=accPage.doSearch("MacBook");
		productInfoPage=searchResultPage.selectProductFromResults("MacBook Pro");
		shoppingCartPage=productInfoPage.navigateToAddToCart();
		
	}
	@Description("Shopping cart Page final Price Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void getProductFinalPriceTest() {
		
		String finalPrice = shoppingCartPage.getFinalPriceOfAddedProduct();
		Assert.assertEquals(finalPrice, "$14,000.00");
	}
//	@Test(priority=2,enabled=true)
//	public void addedProductInfo() {
//		Map<String, String> info = shoppingCartPage.getRewardPointOfAddedProduct();
//		softAssert.assertTrue(info.get("Name").equals("MacBook"));
//		softAssert.assertTrue(info.get("Reward Points").equals("800"));
//		softAssert.assertAll();
//	}

}
