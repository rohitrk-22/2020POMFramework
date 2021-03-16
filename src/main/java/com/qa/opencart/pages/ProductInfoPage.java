package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage 

{
	private WebDriver driver;
	ElementUtil elementUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By productQuantity = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private By successMsg=By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By itemsButton =By.id("cart");
	private By viewCart =By.cssSelector("ul.list-inline i.fa-shopping-cart");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil= new ElementUtil(driver);
	}
	
	
	
	public String getProductHeaderText() {
		
		return elementUtil.doGetElementText(productHeader).trim();
		
		
	}
	
	public int getProducrImagesCount() {
		return elementUtil.getElements(productImages).size();
	}
	
	
	public Map<String, String> getProductInformation() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("Name", getProductHeaderText());
		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
		System.out.println("Total Meta Data:"+ productMetaDataList.size());
		for(WebElement e: productMetaDataList) {
			String meta[]= e.getText().split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		List<WebElement> productpriceDataList = elementUtil.getElements(productPriceData);
		productInfoMap.put("Price", productpriceDataList.get(0).getText().trim());
		productInfoMap.put("ExtraTAX Price", productpriceDataList.get(1).getText().split(":")[1].trim());
		
//		for(WebElement e :productpriceDataList) {
//			String meta[]=e.getText().split(":");
//			String metakey=meta[0].trim();
//			String metaValue=meta[1].trim();
//			productInfoMap.put(metakey, metaValue);
//		}
		
		return productInfoMap;
		}
	
	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(productQuantity, qty);
	}
	
	public void addToCart() {
		elementUtil.doActionsClick(addToCartButton);
	}
	
	public String getSuccessMsg() {
		 elementUtil.printElementsTexts(successMsg, 10);
		return elementUtil.doGetElementText(successMsg);
	}
	
	public ShoppingCartPage navigateToAddToCart() {
		elementUtil.doActionsClick(addToCartButton);
		elementUtil.waitForPresenceOfElement(successMsg, 10);
		elementUtil.doClick(itemsButton);
		elementUtil.doClick(viewCart);
		return new ShoppingCartPage(driver);
	}

	
}
