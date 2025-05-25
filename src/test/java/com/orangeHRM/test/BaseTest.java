package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class BaseTest extends BaseClass {

	@Test
	public void setupTest() {
		// TODO Auto-generated method stub
		String title = driver.getTitle();
		Assert.assertEquals(title, "OrangeHRM");
	}

}
