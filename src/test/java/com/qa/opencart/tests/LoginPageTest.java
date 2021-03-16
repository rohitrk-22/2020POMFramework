package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Story("US-101: Design login page for demcocart app with login title and forgit link")
@Epic("US-100:Design login page fearure")
public class LoginPageTest extends BaseTest
{
	
	@Description("Login Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1) 
	public void loginPageTitleTest() {
		String title=loginpage.getLoginPageTitle();
		System.out.println("Login Page Title is:"+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("ForgotPassword Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2)
	public void forgotpwdLinkTest() {
		Assert.assertTrue(loginpage.isForgotLinkExit());
	}
	@Description("Login Test with correct Credential..")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void loginTest() {
		
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		String title=accPage.getAccountPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}


}
