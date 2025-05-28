package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	
	protected Properties prop;
	public static WebDriver driver;
	
	@BeforeSuite
	public void loadConfig() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
		
		
	}
	
	private void launchBrowser() {
		if(prop.getProperty("browser").equalsIgnoreCase("Chrome"))
			driver= new ChromeDriver();
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(prop.getProperty("browser").equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else
			System.out.println("Please check the browser name");
	}
	public void configureBrowser() {
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void setup() throws Exception {
		launchBrowser();
		configureBrowser();
		staticWait(5);
		}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void staticWait(int sec) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(sec));
	}
	

}
