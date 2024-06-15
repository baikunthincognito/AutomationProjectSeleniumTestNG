package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorials.qa.data.Constants;

public class Base {
	
	WebDriver driver;
	public Properties prop, propTestData;
	public File propFile, propFileTestData;
	public FileInputStream fis, fisTestData;
	public Base()
	{
		// Properties File Loading
		prop = new Properties();
		propTestData = new Properties();
		propFile = new File(System.getProperty("user.dir")
				+"\\src\\main\\java\\com\\tutorials\\qa\\config\\config.properties");
		propFileTestData = new File(System.getProperty("user.dir")
				+"\\src\\main\\java\\com\\tutorials\\qa\\data\\testData.properties");
		try {
			   fis = new FileInputStream(propFile);
			   fisTestData = new FileInputStream(propFileTestData);
			   prop.load(fis);
			   propTestData.load(fisTestData);
			   
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndLaunchApplication()
	{
		String browserName = prop.getProperty("browser").toLowerCase();

		switch (browserName) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Oops!!! Something Went Wrong!!! Given Browser Name is not Configured");
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
