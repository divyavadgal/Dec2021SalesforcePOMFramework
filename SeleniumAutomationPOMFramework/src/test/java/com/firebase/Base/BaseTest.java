package com.firebase.Base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.firebase.pages.homepages.HomePage;
import com.firebase.pages.loginpages.LoginPage;
import com.firebase.utility.CommmonUtilities;
import com.firebase.utility.Constants;
import com.firebase.utility.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected static WebDriver driver;
	
	protected static GenerateReports report=GenerateReports.getInstance();
	protected static LoginPage loginPage;
	protected static HomePage homePage;
	
	@BeforeTest
	public void setUp() {
		System.out.println("Before classs is executing");
		report.startExtentReport();
		System.out.println("extent report document is created");
	}
	
	@Parameters({"browser"})
	@BeforeMethod
	public void initialSetUp(String browserName,Method method){
		report.startSingleTestReport(method.getName());
		System.out.println("Before method is executing");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(CommmonUtilities.getApplicationProperty("url"));
		loginPage=new LoginPage(driver);
		homePage=new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			report.logTestpassed();
		} else if(result.getStatus()==ITestResult.FAILURE) {
			report.logTestFailed();
			String path=takescreenshot();
			try {
				report.logger.addScreenCaptureFromPath(path);
				//report.logger.addScreenCaptureFromPath(path, path)
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		//closeAllDriver();
		driver.quit();
	}
	
	@AfterTest
	public void endUp() {
		report.endTestReport();
	}
	
	public static String takescreenshot() {
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//D:\BatchProjectCodes\Dec21\SeleniumAutomation\screenshots\firebase.jpg
		String filePath=Constants.SCREENSHOT_PATH+"firebase.jpg";
		File DestFile=new File(filePath);
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "D://BatchProjectCodes//Dec21//SeleniumAutomation//screenshots//firebase.jpg";
	}
	

}
