package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Story("US-105: Design SelectProductFromResults page for demcocart app with selection of product")
@Epic("US-104:Design SelectProductFromResults page fearure")
public class SelectProductFromResultsTest extends BaseTest{
	
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void SelectProductFromResultsSetUp() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		searchResultPage=accPage.doSearch("macbook");
		
	}
	@Description("Select product Page prduct selection Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void selectProductTest() {
	
		
		productInfoPage=searchResultPage.selectProductFromResults("MacBook Pro");
		
		String actualHeader = productInfoPage.getProductHeaderText();
		
		softAssert.assertEquals(actualHeader, "MacBook Pro",Error.PRODUCT_HEADER_NOT_FOUND);
		softAssert.assertEquals(productInfoPage.getProducrImagesCount(), Constants.PRODUCT_IMAGES_COUNT_FOR_MACBOOK);
		softAssert.assertAll();
	}
	
	

}
