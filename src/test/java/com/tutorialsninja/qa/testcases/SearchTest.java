package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

public class SearchTest extends Base{
	WebDriver driver;
	
	SearchTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndLaunchApplication();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test (priority = 1)
	public void verifySearchWithValidProduct()
	{
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
	
	    WebElement product = driver.findElement(By.linkText("HP LP3065"));
	    Assert.assertTrue(product.isDisplayed(), "Product not found after search.");
	}
	
	@Test (priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Honda");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
	
	    WebElement noSearchResultMsg = driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']"));
	    Assert.assertTrue(noSearchResultMsg.isDisplayed(), "Product found after search.");
	}
	
	@Test (priority = 3)
	public void verifySearchWithoutProductData()
	{
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Honda");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
	
	    WebElement noSearchResultMsg = driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']"));
	    Assert.assertTrue(noSearchResultMsg.isDisplayed(), "Product found after search.");
	}

}
