package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(name="email")
	private WebElement mailId;
	
	@FindBy(name="telephone")
	private WebElement telephone;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPassword;
	
	@FindBy(name="agree")
	private WebElement privacyPolicycheckBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement submitButton;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement accountCreatedHeading;
	
	@FindBy(xpath="//input[@name='newsletter' and @value='1']")
	private WebElement YesNewsLetterHeading;
	
	@FindBy(xpath="//div[contains (@class,'alert-dismissible')]")
	private WebElement duplicateEmailMsg;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement PrivacyConcernErrorMsg;
	
	@FindBy(xpath="//input[@name='firstname' and @id='input-firstname']//following::div[1]")
	private WebElement FirstNameErrorMsg;
	
	@FindBy(xpath="//input[@name='lastname' and @id='input-lastname']//following::div[1]")
	private WebElement LastNameErrorMsg;
	
	@FindBy(xpath="//input[@name='email' and @id='input-email']//following::div[1]")
	private WebElement EmailErrorMsg;
	
	@FindBy(xpath="//input[@name='telephone' and @id='input-telephone']//following::div[1]")
	private WebElement TelephoneErrorMsg;
	
	@FindBy(xpath="//input[@name='password' and @id='input-password']//following::div[1]")
	private WebElement passwordErrorMsg;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String fname) {
		firstNameField.sendKeys(fname);
	}
	
	public void enterLastName(String lname) {
		lastNameField.sendKeys(lname);
	}
	public void enterMailID(String mail) {
		mailId.sendKeys(mail);
	}
	public void enterTelephone(String phone) {
		telephone.sendKeys(phone);
	}
	public void Enterpassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void ConfirmPassword(String Cpassword) {
		confirmPassword.sendKeys(Cpassword);
	}
	public void clickOnPrivacyButton() {
		privacyPolicycheckBox.click();
	}
	public AccountSuccessPage ClickOnSubmitBTN() {
		submitButton.click();
		return new AccountSuccessPage(driver);
	}
	public void ClickOnYesNewsLetter() {
		YesNewsLetterHeading.click();
	}
	
	public String getDuplicateEmailWarningMsg() {
		String msg = duplicateEmailMsg.getText();
		return msg;
	}
	
	public String getFirstNameErrorMsg() {
		String msg = FirstNameErrorMsg.getText();
		return msg;
	}
	
	public String getLastNameErrorMsg() {
		String msg = LastNameErrorMsg.getText();
		return msg;
	}
	
	public String getEmailErrorMsg() {
		String msg = EmailErrorMsg.getText();
		return msg;
	}
	
	public String getTelephoneErrorMsg() {
		String msg = TelephoneErrorMsg.getText();
		return msg;
	}
	
	public String getpasswordErrorMsg() {
		String msg = passwordErrorMsg.getText();
		return msg;
	}
	public String getPrivacyConcernErrorMsg() {
		String msg = PrivacyConcernErrorMsg.getText();
		return msg;
	}
	public AccountSuccessPage RegisterAccountwithMandatoryFields(String fname,String lname,String mail,String phone,String pwd,String Cpassword) {
		firstNameField.sendKeys(fname);
		lastNameField.sendKeys(lname);
		mailId.sendKeys(mail);
		telephone.sendKeys(phone);
		password.sendKeys(pwd);
		confirmPassword.sendKeys(Cpassword);
		privacyPolicycheckBox.click();
		submitButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage RegisterAccountwithAllFields(String fname,String lname,String mail,String phone,String pwd,String Cpassword) {
		firstNameField.sendKeys(fname);
		lastNameField.sendKeys(lname);
		mailId.sendKeys(mail);
		telephone.sendKeys(phone);
		password.sendKeys(pwd);
		confirmPassword.sendKeys(Cpassword);
		YesNewsLetterHeading.click();
		privacyPolicycheckBox.click();
		submitButton.click();
		return new AccountSuccessPage(driver);
	}
}

