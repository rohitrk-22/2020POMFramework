package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	 ElementUtil elementUtil ;
	
	private By logo = By.cssSelector("#logo");
	private By AccHeaders= By.cssSelector("div#content h2");
	private By searchField =By.xpath("//input[@name='search']");
	private By searchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		 elementUtil = new ElementUtil(driver);
	}
	
	public boolean isLogoExist() {
		return elementUtil.doIsDisplayed(logo);
	}
	
	public String getAccountPageTitle() {
		return elementUtil.waitForTitleIs(5, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	public int getAccountPageHeaderCount() {
		return elementUtil.getElements(AccHeaders).size();
	}
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement> AcctHeaderList=elementUtil.getElements(AccHeaders);
		
		List<String>accList =new ArrayList<String>();
		for(WebElement e : AcctHeaderList) {
			accList.add(e.getText());
		}
		return accList;
	}
	public SearchResultPage doSearch(String productName) {
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}
	
	
}
