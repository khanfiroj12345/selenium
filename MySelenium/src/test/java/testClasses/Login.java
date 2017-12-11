package testClasses;
import org.openqa.selenium.*;
import  utils.BrowserFactory;
import utils.XlsReader;
import java.util.Properties;
import pom.utils.PropertyReader;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import pom.pages.LoginPage;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class Login 
{
	WebDriver driver;
	Properties prop=PropertyReader.read(System.getProperty("user.dir")+"\\src\\test\\resources\\urls\\url.properties");
	String url=prop.getProperty("loginUrl");
	SoftAssert sassert=new SoftAssert();
	 {
		 //initializing Atu Reporter using atu.properties file
	   System.setProperty("atu.reporter.config",System.getProperty("user.dir")+"\\src\\test\\resources\\atu-jars\\atu.properties");
	 }
	 

	@BeforeTest
	  public void setup() 
	  {
		  driver=BrowserFactory.getBrowser("IE");
		  driver.get(url);
		  driver.manage().window().maximize();
		  ATUReports.setWebDriver(driver);		  	  
	  }
	
	
	@Test(dataProvider="DP")
	  public void login(String username, String password) 
	  {
		  driver.get(url);
		  //Adding a step in Report
		  ATUReports.setAuthorInfo("Firoj Khan", "12-Nov-2013 3:46", "1.2");
		  LoginPage obj=new LoginPage(driver);
		  obj.login(username, password);
		  ATUReports.add("After Login",LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		  Properties prop=PropertyReader.read(System.getProperty("user.dir")+"\\src\\test\\resources\\OR\\loginOR.properties");
		  sassert.assertEquals((driver.getTitle()),(prop.getProperty("loggedInPageTitle")));
		  sassert.assertAll();
	  }
	
	
	@AfterTest
	  public void afterTest() 
	  {
		  try
		  {
		  Thread.sleep(500);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  driver.close();
	  }
	
	
	@DataProvider(name="DP")
	  public Object[][] readData()
	  {   
		  XlsReader reader=new XlsReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\data.xlsx");
		  Object[][] data= reader.dataForDataProvider("Sheet1");
		  return data;
	  }
}
