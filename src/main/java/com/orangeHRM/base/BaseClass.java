package com.orangeHRM.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	
	
	protected Properties prop;
	protected WebDriver driver;
	
	@BeforeMethod
	public void setup() throws Exception {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
		
		
		if(prop.getProperty("browser").equalsIgnoreCase("Chrome"))
			driver= new ChromeDriver();
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		
		
		
	}
	

}
