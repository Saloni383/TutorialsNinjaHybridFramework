
package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	@FindBy(linkText="HP LP3065")
	private WebElement searchElement;
	
	
	
	@FindBy(xpath="//div[@id=\"content\"]/h2//following-sibling::p")
	private WebElement InvalidsearchElement;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean displayStatusOfValidHPProduct() {
		boolean displayCheck = searchElement.isDisplayed();
		return displayCheck;
	}
	
	public String displayMessageOfInvalidHPProduct() {
		String invalidSearchElementMsg = InvalidsearchElement.getText();
		return invalidSearchElementMsg;
	}
	
}
