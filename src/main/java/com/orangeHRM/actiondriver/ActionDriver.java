package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public ActionDriver(WebDriver driver) {
		this.driver= driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	public void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element is not clickabe");
		}
	}
	
	public void waitForElementVisiblity(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible");
		}
	}
	
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("unable to click element"+e.getMessage());
		}
	}
	public void enterText(By by,String value) {
		try {
			waitForElementVisiblity(by);
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			System.out.println("unable to enter text"+e.getMessage());
		}
	}
	
	public boolean isDisplayed(By by) {
		
			waitForElementVisiblity(by);
			if(driver.findElement(by).isDisplayed())
				return true;
			return false;
		
	}
	
	public void scrollToAnElement(By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0],scrollIntoView(true)",driver.findElement(by));
	}

}
