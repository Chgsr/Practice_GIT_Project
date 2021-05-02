package com.guna.automation.testcases;

import org.testng.annotations.Test;

import com.guna.BaseTest.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class Test2 extends BaseTest {
	
	@Test
	public void Demo() {
		getExtenttest().log(LogStatus.PASS, "Browser Is Opened");
		
		GetDriver().get("https://www.gmail.com");
		getExtenttest().log(LogStatus.PASS, "URL is Entered");

	}

}
