package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// objects
	@FindBy(xpath = "//a[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchOption;

	@FindBy(xpath = "//span[@class='input-group-btn']")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void clickonMyAccount() {
		myAccountDropMenu.click();
	}

	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);

	}

	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);

	}

	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}

	public void ClickOnSearchOption(String searchItem) {
		searchOption.sendKeys(searchItem);
	}

	public SearchPage ClickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}

	public SearchPage searchForProduct(String searchItem) {
		searchOption.sendKeys(searchItem);
		searchButton.click();
		return new SearchPage(driver);
	}
}
