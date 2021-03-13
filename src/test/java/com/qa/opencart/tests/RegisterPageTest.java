package com.qa.opencart.tests;



import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Error;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.TestUtility;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Story("US-104: Design Registration page for demcocart app with firstname,lastname,enail,password,telephone,policy")
@Epic("US-103:Design Registration page fearure")
public class RegisterPageTest extends BaseTest
{
	
	@BeforeClass
	public void registerPageSetUp() {
		registerPage=loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object[][] testData = ExcelUtil.getTestData("Register");
		return testData;
	}
	
	@Description("Register Page user registration Test...")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test(priority=1,dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstname,String lastname,
			String telephoneno,String password,String subscribe)
	{
		Assert.assertTrue(registerPage.userregistration(firstname,lastname,
				TestUtility.getRandomEmail(),telephoneno,password,subscribe),Error.ACCOUNT_REGISTRATION_FAILED);
	}

}
