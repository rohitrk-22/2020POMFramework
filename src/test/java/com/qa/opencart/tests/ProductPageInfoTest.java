package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Story("US-103: Design Product  page for demcocart app with productinfo and addtocart")
@Epic("US-102:Design Product page fearure")
public class ProductPageInfoTest extends BaseTest {
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void accSetUp() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
//		searchResultPage=accPage.doSearch("MacBook");
//		productInfoPage=searchResultPage.selectProductFromResults("MacBook Pro");
		
		
	}
	@Description("Product Page Info data Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void productInfoDataTest() {
		searchResultPage=accPage.doSearch("MacBook");
		productInfoPage=searchResultPage.selectProductFromResults("MacBook Pro");
		Map<String, String> actProductInfoMao = productInfoPage.getProductInformation();
		softAssert.assertTrue(actProductInfoMao.get("Name").equals("MacBook Pro"));
		softAssert.assertTrue(actProductInfoMao.get("Brand").equals("Apple"));
		softAssert.assertTrue(actProductInfoMao.get("Price").equals("$2,000.00"));
		softAssert.assertAll();
	}
	@Description("Product Page AddToCart Test...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=2)
	public void addToCartTest() {
//		productInfoPage.selectQuantity("2");
		productInfoPage.addToCart();
		String msg = productInfoPage.getSuccessMsg();
		Assert.assertTrue(msg.contains("Success"));
	}
	@Description("Product Page SHOPPINGCARTPAGE NAVIGATIONTest...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void navigateToCartTest() {
		shoppingCartPage=productInfoPage.navigateToAddToCart();
		String shoppingCartPageTitle = shoppingCartPage.getShoppingCartPageTitle();
		Assert.assertEquals(shoppingCartPageTitle, Constants.SHOPPING_CART_PAGE_TITLE);
	}
	
	
	
	
}
