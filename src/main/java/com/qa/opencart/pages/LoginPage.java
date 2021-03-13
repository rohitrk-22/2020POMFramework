package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	
	private ElementUtil elementUtil;
	private WebDriver driver;
	//page object
	private By username= By.id("input-email");
	private By password = By.id("input-password");
	private By logginButton = By.xpath("//input[@type='submit']");
	private By forgotpwd = By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By register= By.linkText("Register");
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		
	}
	//page actions
	@Step("Getting Login page title...")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleIs(5, Constants.LOGIN_PAGE_TITLE);
		
	}
	@Step("Checking forgot password link...")
	public boolean isForgotLinkExit() {
		return elementUtil.doIsDisplayed(forgotpwd);
		
	}
	
	@Step("login with username:{0} and password: {1}")
	public AccountsPage doLogin(String un,String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doActionsClick(logginButton);
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		elementUtil.doClick(register);
		return new RegisterPage(driver);
	}
}
