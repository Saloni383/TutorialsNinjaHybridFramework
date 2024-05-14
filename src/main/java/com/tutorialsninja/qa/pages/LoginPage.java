package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	@FindBy(name = "email")
	private WebElement eMailAdd;

	@FindBy(id = "input-password")
	private WebElement pwd;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtn;

	@FindBy(xpath = "//div[contains(@class,\"alert-dismissible\")]")
	private WebElement emailPassowrdNotMatchingWarning;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void enterEmail(String email) {
		eMailAdd.sendKeys(email);
	}

	public void enterPassword(String password) {
		pwd.sendKeys(password);
	}

	public AccountPage ClickOnLoginBTN() {
		loginBtn.click();
		return new AccountPage(driver);
	}

	public AccountPage login(String email, String password) {
		eMailAdd.sendKeys(email);
		pwd.sendKeys(password);
		loginBtn.click();
		return new AccountPage(driver);
	}

	public String retrieveEmailPasswordNotMatchingWarningMessageText() {
		String text = emailPassowrdNotMatchingWarning.getText();
		return text;
	}

}
