package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage  {
	
	
	ElementUtil elementUtil;
	private WebDriver driver;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By subscribeYes= By.xpath("(//label[@class='radio-inline']/input)[1]");
	private By subscribeNo= By.xpath("(//label[@class='radio-inline']/input)[2]");
	private By agreePolicy= By.name("agree");
	private By continueButton= By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMsg=By.cssSelector("div#content h1");
	private By logout=By.linkText("Logout");
	private By Register=By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		
		elementUtil = new ElementUtil(driver);
	}
	
	public boolean userregistration(String userName,String lastName,String eMail,String telephone,
									String password,String subscribe	) {
		
		elementUtil.doSendKeys(this.firstname, userName);
		elementUtil.doSendKeys(this.lastname, lastName);
		elementUtil.doSendKeys(this.email, eMail);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.contains("yes")) {
			elementUtil.doClick(subscribeYes);
			
		}
		else{
			elementUtil.doClick(subscribeNo);
			
		}
		
		elementUtil.doClick(this.agreePolicy);
		elementUtil.doClick(continueButton);
		elementUtil.waitForVisiblilityOfElement(successMsg, 5);
		
		String msg = elementUtil.doGetElementText(successMsg);
		
		if(msg.contains(Constants.ACCOUNT_CREATION_SUCCESS_MSG)) {
			
			elementUtil.doClick(logout);
			elementUtil.doClick(Register);
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	

	

}
