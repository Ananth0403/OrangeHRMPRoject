package com.orangehrm.test;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.orangehrm.base.BaseClass;

public class TestListener implements ITestListener {
    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;
    ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseClass.driver;
        try {
            String path = ScreenshotUtility.takeScreenshot(driver, result.getMethod().getMethodName());
            testThread.get().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}
