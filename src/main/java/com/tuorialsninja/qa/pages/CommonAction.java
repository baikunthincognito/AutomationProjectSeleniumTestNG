package com.tuorialsninja.qa.pages;

import org.openqa.selenium.WebElement;

public class CommonAction {

	public static void clickOn(WebElement element, String nameOfEntity, String pageName, String typeOfEntity)
	{
		element.click();
		System.out.println("WebElement: "+element+ " named as: "+nameOfEntity+" exists on page: "+pageName+" which is having type: "+typeOfEntity+" is clicked.");
	}
}
