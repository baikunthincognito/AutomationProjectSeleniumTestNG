package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tuorialsninja.qa.pages.LoginPageFactory;
import com.tutorialninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

// Comment by Tester-01

public class LoginTest extends Base {
	WebDriver driver;
	LoginPageFactory loginFeature;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializeBrowserAndLaunchApplication();
		loginFeature = new LoginPageFactory(driver);
		loginFeature.clickMyAccountClickableNavBar(loginFeature.getMyAccountClickableNavBar(), "My Account", "HomePage", "Clickable Navigation Bar");
		loginFeature.clickOnLoginOptionClickableText(loginFeature.getLoginOptionClickableText(), "Option Login", "HomePage", "Clickable Text Login Option");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));

		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"User is not logged in.");

		driver.quit();
	}

	@DataProvider(name = "invalidCredentialSupplier")

	public Object[][] suppllierData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");

		return data;
	}

	/*
	 * Example of Data Provider
	 */
//	@Test(priority = 2, dataProvider = "invalidCredentialSupplier")
//	public void loginWithInvalidCredentials(String email, String password) throws InterruptedException {
//		String un = Utilities.generateRandomMailWithTimeStamp();
//		driver.findElement(By.id("input-email")).sendKeys(email);
//		driver.findElement(By.id("input-password")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText()
//				.contains(propTestData.getProperty("invalidCredentialsWarningMessage")),
//				"Expected Message is not displayed.");
//
//		driver.quit();
//	}

	@Test(priority = 2, dataProvider = "invalidCredentialSupplier")
	public void loginWithInvalidCredentials(String email, String password) throws InterruptedException {
		//String un = Utilities.generateRandomMailWithTimeStamp();
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText()
						.contains(propTestData.getProperty("invalidCredentialsWarningMessage")),
				"Expected Message is not displayed.");

		driver.quit();
	}

	@Test(priority = 3)
	public void loginWithInValidEmailAndValidPassword() throws InterruptedException {
		String un = Utilities.generateRandomMailWithTimeStamp();
		driver.findElement(By.id("input-email")).sendKeys(un);
		driver.findElement(By.id("input-password")).sendKeys("Coolbns@002");

		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText()
				.contains("Warning: No match for E-Mail Address and/or Password."),
				"Expected Message is not displayed.");

		driver.quit();
	}

	@Test(priority = 4)
	public void loginWithValidEmailAndInvalidPassword() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys("Coolbns@003");

		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText()
				.contains("Warning: No match for E-Mail Address and/or Password."),
				"Expected Message is not displayed.");

		driver.quit();
	}

	@Test(priority = 5)
	public void loginWithoutProvidingAnyUnPassword() throws InterruptedException {
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");

		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText()
				.contains("Warning: No match for E-Mail Address and/or Password."),
				"Expected Message is not displayed.");

		driver.quit();
	}

}
