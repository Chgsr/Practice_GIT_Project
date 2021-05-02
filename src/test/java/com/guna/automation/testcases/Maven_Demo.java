package com.guna.automation.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.guna.BaseTest.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

@Test
public class Maven_Demo extends BaseTest {
	
	
	public void TestDemo() {
		getExtenttest().log(LogStatus.PASS, " Browser is Openend");

		System.out.println(" Hi Shekar reddy");
		
		GetDriver().get("Https://www.gmail.com");
		getExtenttest().log(LogStatus.PASS, " URL Is entered as");
		System.out.println(GetDir());
		
		GetDriver().findElement(By.id("identifierId")).sendKeys("gunashekar");
		getExtenttest().log(LogStatus.PASS, " entered gmail id as ");
		
		
		
	}

}
