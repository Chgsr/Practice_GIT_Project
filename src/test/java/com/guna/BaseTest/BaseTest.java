package com.guna.BaseTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private static String curDir;
	private static WebDriver driver;
	private static ExtentReports extentreports;
	private static ExtentTest extenttest;
	

	
	
	
	
	@Parameters("Browsername")
	@BeforeSuite
	public void openBrowser(@Optional("chrome") String Browsername){
		
		curDir=System.getProperty("user.dir");
		
		
		if(Browsername.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			init();
		}
		else if(Browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			init();
		}
		else if(Browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			init();
		}
		else if(Browsername.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			init();
		}


	
}

private void init() {
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
}

@AfterSuite
public void CloseBrowser() {

	if(driver!=null)
	{
		driver.quit();
	}
	else
	{
		System.out.println("Null Pointer Exception");
	}
	
	
}
@BeforeTest
private void initreports() {
	extentreports=new ExtentReports(curDir+"\\Reports\\reports.html");
	Map<String, String> map= new HashMap<String, String>();
	map.put("User", "Reddy");
	map.put("PHNUM", "8463904595");
	extentreports.addSystemInfo(map);

}
@AfterTest
private void generateReports() {
	extentreports.close();
	

}

public WebDriver  GetDriver() {
	
	return driver;
}

public String GetDir() {

	return curDir;
}

@BeforeMethod
public void beforemethod(Method method) {
	
	System.out.println(" Test case is Started: " +method.getName());
	extenttest= extentreports.startTest(method.getName());
	
	extenttest.log(LogStatus.PASS, "Test case is Started: "+method.getName() );

}

@AfterMethod
public void aftermethod(ITestResult result, Method method) throws IOException {

	
	System.out.println(method.getName());
	
	
	if(result.getStatus()==ITestResult.SUCCESS)
	{
		System.out.println(" Test case Is passed: "+method.getName());
		extenttest.log(LogStatus.PASS, " Test case Is Passed: " +method.getName());
		System.out.println(" Taking Screen shot "+method.getName());
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File img = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(img , new File(GetDir()+"\\Screenshots\\"+method.getName()+".jpeg"));
	}
	else if(result.getStatus()==ITestResult.FAILURE)
	{
		System.out.println(" Test case is Failed : " +method.getName());
		extenttest.log(LogStatus.FAIL, " Test case Is Passed: " +method.getName());
		System.out.println(" Taking Screen shot "+method.getName());
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File img = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(img , new File(GetDir()+"\\Screenshots"+method.getName()+".jpeg"));
	}
	else if (result.getStatus()==ITestResult.SKIP)
	{
		System.out.println(" Test case is Skipped: "+method.getName());
		extenttest.log(LogStatus.SKIP, " Test case Is Passed: " +method.getName());
		System.out.println(" Taking Screen shot ");
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File img = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(img , new File(GetDir()+"\\Screenshots\\"+method.getName()+".jpeg"));
	}
	extentreports.endTest(extenttest);
	extentreports.flush();
	
	
	
	}

	public static ExtentTest getExtenttest() {
	return extenttest;
	}



























}