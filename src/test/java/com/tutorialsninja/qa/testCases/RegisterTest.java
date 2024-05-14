package com.tutorialsninja.qa.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.qa.base.base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends base {

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	@BeforeMethod
	public void Setup() {

		try {
			loadPropertiesFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver = initializeBrowserAndOpenApplicationURL(p.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage= homePage.navigateToRegisterPage();
		
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyRegisteringAccountWithMandatoryFields() {
		
		
		accountSuccessPage =registerPage.RegisterAccountwithMandatoryFields(dataProp.getProperty("fname"), dataProp.getProperty("lname"), Utilities.generateTimeStamp(), dataProp.getProperty("telephone"), dataProp.getProperty("registerPassword"), dataProp.getProperty("registerPassword"));
		Assert.assertEquals(accountSuccessPage.getSuccessHeadingMessage(), dataProp.getProperty("SuccessExpMsg"), "Account has not been created successfully");
	}

	@Test(priority = 2)
	public void VerifyRegisteringAccountByProvidingAllFields() {
		
		accountSuccessPage =registerPage.RegisterAccountwithAllFields(dataProp.getProperty("fname"), dataProp.getProperty("lname"), Utilities.generateTimeStamp(), dataProp.getProperty("telephone"), dataProp.getProperty("registerPassword"), dataProp.getProperty("registerPassword"));
		Assert.assertEquals(accountSuccessPage.getSuccessHeadingMessage(), dataProp.getProperty("SuccessExpMsg"), "Account has not been created successfully");
	}

	@Test(priority = 3)
	public void VerifyRegisteringExistingAccountCredentials() {
		
		accountSuccessPage =registerPage.RegisterAccountwithAllFields(p.getProperty("name"), p.getProperty("surname"),p.getProperty("validEmail"), dataProp.getProperty("telephone"),p.getProperty("password"),p.getProperty("password"));
		Assert.assertEquals(registerPage.getDuplicateEmailWarningMsg(),dataProp.getProperty("EmailWarning"), "Warning Message doesn't displayed");
	}

	@Test(priority = 4)
	public void VerifyRegisteringAccountWithoutAnyDetails() {

		registerPage.ClickOnSubmitBTN();
		
		Assert.assertTrue(registerPage.getPrivacyConcernErrorMsg().contains(dataProp.getProperty("EX_Warn")),"Privacy Concern Policy Message doesn't displayed");
		
		Assert.assertTrue(registerPage.getFirstNameErrorMsg().contains(dataProp.getProperty("EX_Fname")), "First Name is not Displayed");
	
		Assert.assertTrue(registerPage.getLastNameErrorMsg().contains(dataProp.getProperty("EX_Lname")), "Last Name is not Displayed");
		
		Assert.assertTrue(registerPage.getEmailErrorMsg().contains(dataProp.getProperty("EX_Mail")), "Email is not Displayed");
		
		Assert.assertTrue(registerPage.getTelephoneErrorMsg().contains(dataProp.getProperty("EX_Phone")), "Telephone is not Displayed");
		
		Assert.assertTrue(registerPage.getpasswordErrorMsg().contains(dataProp.getProperty("EX_Password")), "Password is not Displayed");
		
	}
}