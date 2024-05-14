package com.tutorialsninja.qa.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorials.qa.base.base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends base {
	public WebDriver driver;
	LoginPage loginPage;

	@BeforeMethod
	public void setup() {

		try {
			loadPropertiesFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = initializeBrowserAndOpenApplicationURL(p.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(dataProvider = "validDataProviderTest1", priority = 6)
	public void verifyValidLoginCredentials1(String email, String password) {

		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys((password));
		driver.findElement(By.cssSelector("input[type='submit'][value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}
	
	@Test(dataProvider = "validDataProviderTest2", priority = 7)
	public void verifyValidLoginCredentials2(String email, String password) {

		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys((password));
		driver.findElement(By.cssSelector("input[type='submit'][value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}

	@DataProvider(name="validDataProviderTest1")
	public Object[][] supplyData1() {
		Object testdata[][] = { { "salonikaushik.383@gmail.com", "12345" }, { "meerabajaj15@gmail.com", "12345" } };
		return testdata;
	}
	
	@DataProvider(name="validDataProviderTest2")
	public Object supplyData2() throws IOException {
		Object testdata = Utilities.getDataFromExcel("Login");
		return testdata;
	}

	@Test(priority = 1)
	public void verifyValidLoginCredentials() {
		
		LoginPage loginPage = new LoginPage(driver);
		AccountPage accountPage = loginPage.login(p.getProperty("validEmail"), p.getProperty("password"));
		Assert.assertTrue(accountPage.getDisplayStatusOfAccountInformationOption());
		
	}

	@Test(priority = 2)
	public void verifyInvalidLoginCredentials() {
		
		loginPage.login(Utilities.generateTimeStamp(),dataProp.getProperty("InvalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("LoginEXPCondition")),"Expected Condition is equal to Actual Condition");
	}

	@Test(priority = 3)
	public void verifyInvalidEmailAndValidPassword() {
		
		loginPage.login(Utilities.generateTimeStamp(),p.getProperty("password"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("LoginEXPCondition")),"Expected Condition is equal to Actual Condition");
	}

	@Test(priority = 4)
	public void verifyValidEmailAndInvalidPassword() {

		loginPage.login(p.getProperty("validEmail"),Utilities.generateTimeStamp());
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("LoginEXPCondition")),"Expected Condition is equal to Actual Condition");
	}

	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {

		loginPage.ClickOnLoginBTN();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("LoginEXPCondition")),"Expected Condition is equal to Actual Condition");
	}
}
