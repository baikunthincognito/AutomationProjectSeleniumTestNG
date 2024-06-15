package com.tuorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory extends CommonAction {
	
	WebDriver driver;
	
	/*
	 *    Locators on Page
	 */
	
	@FindBy (xpath = "//span[text()='My Account']")
	private WebElement myAccountClickableNavBar; 
	
	@FindBy (linkText = "Login")
	private WebElement loginOptionClickableText;
	
	/*
	 *    Methods Associated with Locators
	 */
	
	public WebElement getMyAccountClickableNavBar() {
		return myAccountClickableNavBar;
	}

	public void clickMyAccountClickableNavBar(WebElement element, String nameOfEntity, String pageName, String typeOfEntity) {
		clickOn(element, nameOfEntity, pageName, typeOfEntity);
	}
	
	public WebElement getLoginOptionClickableText() {
		return loginOptionClickableText;
	}

	public void clickOnLoginOptionClickableText(WebElement element, String nameOfEntity, String pageName, String typeOfEntity) {
		clickOn(element, nameOfEntity, pageName, typeOfEntity);
	}



	/*
	 *    PageFactory Initialization
	 */
	public LoginPageFactory(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
