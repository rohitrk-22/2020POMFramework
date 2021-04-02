package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage 

{
	private WebDriver driver;
	ElementUtil elementUtil;
	private By totalPrice=By.cssSelector("div#content div.col-sm-offset-8 td:nth-of-type(2)");
	private By rewardedPoint=By.tagName("small");
	private By productName=By.cssSelector("div.table-responsive td.text-left:nth-of-type(2) a");
	
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public String getShoppingCartPageTitle() {
		return elementUtil.waitForTitleIs(15, Constants.SHOPPING_CART_PAGE_TITLE);
	}
	
	public String getFinalPriceOfAddedProduct() {
		return elementUtil.doGetElementText(totalPrice);
	}
	
	public String getNameFinalOfAddedProduct() {
		return elementUtil.doGetElementText(productName);
	}
	
	public Map<String, String> getRewardPointOfAddedProduct() {
		Map<String, String> rewardPoint = new HashMap<String, String>();
		List<WebElement> points = elementUtil.getElements(rewardedPoint);
		for(WebElement e: points) {
			 String meta[]=e.getText().split(":");
			 String key = meta[0].trim();
			 String value = meta[1].trim();
			 rewardPoint.put(key, value);
		}
		
		
		return rewardPoint;
	}
	

}
