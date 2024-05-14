package com.tutorialsninja.qa.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.qa.base.base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class searchTest extends base {
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	@BeforeMethod
	public void Setup() {

		try {
			loadPropertiesFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = initializeBrowserAndOpenApplicationURL(p.getProperty("browser"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyWithValidProduct() {
		
		searchPage=homePage.searchForProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfValidHPProduct(),"Valid Display product not displayed in search option");
		
	}

	@Test(priority = 2)
	public void verifyWithInvalidProduct() {
		
		searchPage=homePage.searchForProduct(dataProp.getProperty("invalidProduct"));
		//Assert.assertEquals(searchPage.displayMessageOfInvalidHPProduct(), dataProp.getProperty("NoProductMatchErrorMsg"),"No Product message in Search Result found is displayed");
		Assert.assertEquals(searchPage.displayMessageOfInvalidHPProduct(), "abcd","No Product message in Search Result found is displayed");
	}

	@Test(priority = 3,dependsOnMethods={"verifyWithInvalidProduct","verifyWithValidProduct"})
	public void verifyWithoutAnyProduct() {
		
		searchPage= homePage.ClickOnSearchButton();
		SearchPage searchPage= new SearchPage(driver);
		
		Assert.assertEquals(searchPage.displayMessageOfInvalidHPProduct(), dataProp.getProperty("NoProductMatchErrorMsg"),"No Product message in Search Result found is displayed");
		
	}

}
