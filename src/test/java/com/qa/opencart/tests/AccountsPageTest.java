package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Story("US-102: Design Account page for demcocart app with login,Logo,Section and SearchField")
@Epic("US-101:Design Account page fearure")
public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accPageSetUp() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Description("Account Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void accPageTitleTest() {
		String title = accPage.getAccountPageTitle();
		System.out.println("Account Page Title is "+ title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE,Error.ACC_PAGE_MISMATCHED_ERROR);
	}
	
	@Description("Account Page Logo Test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void accPageLogoTest() {
		
		Assert.assertTrue(accPage.isLogoExist(),Error.LOGO_NOT_AVAILABLE_ERROR);
	}
	
	@Description("Account Page Section count Test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void accPageSectionsCountTest() {
		int sectionCount = accPage.getAccountPageHeaderCount();
		Assert.assertEquals(sectionCount, Constants.ACCOUNT_PAGE_SECTIONS,Error.ACC_PAGE_SECTION_ERROR);
	}
	
	@Description("Account Page Section Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void accPageSectionsTest() {
		List<String> actualAccSecList = accPage.getAccountPageHeadersList();
		System.out.println(actualAccSecList);
		
		Assert.assertEquals(actualAccSecList, Constants.expectedAccSectionList());
	}
	@Description("Account Page Search Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void searchTest() {
		searchResultPage=accPage.doSearch("mac");
		int searchCount = searchResultPage.getProductResultsCount();
		Assert.assertTrue(searchCount>0,Error.SEARCH_NOT_SUCCESSFUL);
	}

}
