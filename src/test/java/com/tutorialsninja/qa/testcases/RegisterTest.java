package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	WebDriver driver;
	
	RegisterTest()
	{
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializeBrowserAndLaunchApplication();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test (priority=1)
	public void registerationWithmandatoryFields() {
	
		driver.findElement(By.id("input-firstname")).sendKeys("Baikunth");
		driver.findElement(By.id("input-lastname")).sendKeys("Shukla");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateRandomMailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("98"+Utilities.generateRandomNumber(8));
		String password = "Test@"+Utilities.generateRandomNumber(3);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.id("input-confirm")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='checkbox'][@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		boolean confirmation = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).isDisplayed();
		Assert.assertTrue(confirmation, "Account is not succesfully created.");
	}
	
	@Test (priority=2)
	public void verifyRegistrationWithAllFileds()
	{
		driver.findElement(By.id("input-firstname")).sendKeys("Baikunth");
		driver.findElement(By.id("input-lastname")).sendKeys("Shukla");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateRandomMailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("98"+Utilities.generateRandomNumber(8));
		String password = "Test@"+Utilities.generateRandomNumber(3);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.id("input-confirm")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Yes']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox'][@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		boolean confirmation = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).isDisplayed();
		Assert.assertTrue(confirmation, "Account is not succesfully created.");
	}
	
	@Test (priority=3)
	public void verifyAccountCreationWithAlreadyRegisteredMailID()
	{
		
		driver.findElement(By.id("input-email")).sendKeys("baikunth.shukla.001@gmail.com");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		boolean warning = driver.findElement(By.xpath("//div[text()='Warning: You must agree to the Privacy Policy!']")).isDisplayed();
		Assert.assertTrue(warning, "Account is succesfully created with already existing mail id.");
	}
	
	@Test (priority=4)
	public void verifyAccountCreationWithoutFillingAnyData() throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(3000);
		boolean warning = driver.findElement(By.xpath("//div[text()='Warning: You must agree to the Privacy Policy!']")).isDisplayed();
		Assert.assertTrue(warning, "Account is succesfully created without filling any data.");
	    //Assertions for all can be done
	}

}
