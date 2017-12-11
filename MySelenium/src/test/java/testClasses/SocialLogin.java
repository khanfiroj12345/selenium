package testClasses;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pom.pages.SocialLoginPage;
import utils.BrowserFactory;
import utils.XlsReader;
import pom.utils.*;
import java.util.Properties;
import java.util.Set;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class SocialLogin 
{
	WebDriver driver;
	Properties prop=PropertyReader.read(System.getProperty("user.dir")+"\\src\\test\\resources\\OR\\socialLoginOR.properties");
	Properties prop2=PropertyReader.read(System.getProperty("user.dir")+"\\src\\test\\resources\\urls\\url.properties");
	String url=prop2.getProperty("socialLoginUrl");
	SoftAssert sassert=new SoftAssert();
	 {
		 //Initializing Atu Reporter
	   System.setProperty("atu.reporter.config",System.getProperty("user.dir")+"\\src\\test\\resources\\atu-jars\\atu.properties");
	 }
	@BeforeTest
	  public void setup() 
	  {
		  driver=BrowserFactory.getBrowser();
		  ATUReports.setWebDriver(driver);	
		  driver.manage().window().maximize();
	  }
	
	/*Calling all Social Login functions one by One.
	 * Login Data will be fetched from Data Providers of different test case*/
	
	
	@Test(priority=40,dataProvider="DPfacebook")
	public void facebookLogin(String username,String password)
	{
		driver.get(url);
		SocialLoginPage obj=new SocialLoginPage(driver);
		obj.socialFacebookLogin(username,password);
		ATUReports.add("After Login",LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		sassert.assertEquals((driver.getTitle()),(prop.getProperty("fb_loggedIn_pageTitle")));
		sassert.assertAll();
	}
	
	
	@DataProvider(name="DPfacebook")
	  public Object[][] readData2()
	  {   
		  XlsReader reader=new XlsReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\data.xlsx");
		  Object[][] data= reader.dataForDataProvider("facebook");
		  return data;
	  }
	
	@Test(priority=10,dataProvider="DPgmail")
	public void gmailLogin(String username,String password)
	{
		driver.get(url);
		SocialLoginPage obj=new SocialLoginPage(driver);
		obj.socialGmailLogin(username,password);
		ATUReports.add("After Login",LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		sassert.assertEquals(driver.getTitle(),(prop.getProperty("gmail_loggedIn_pageTitle")));
		sassert.assertAll();
	}
	
	@DataProvider(name="DPgmail")
	  public Object[][] readData()
	  {   
		  XlsReader reader=new XlsReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\data.xlsx");
		  Object[][] data= reader.dataForDataProvider("gmail");
		  return data;
	  }
	
	@Test(priority=20,dataProvider="DPtwitter")
	public void twitterLogin(String username,String password)
	{
		driver.get(url);
		SocialLoginPage obj=new SocialLoginPage(driver);
		obj.socialTwitterLogin(username,password);
		ATUReports.add("After Login",LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		sassert.assertEquals(driver.getTitle(),(prop.getProperty("twit_loggedIn_pageTitle")));
		sassert.assertAll();
	}
	
	
	@DataProvider(name="DPtwitter")
	  public Object[][] readData3()
	  {   
		  XlsReader reader=new XlsReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\data.xlsx");
		  Object[][] data= reader.dataForDataProvider("twitter");
		  return data;
	  }
	  
	  
	@Test(priority=30,dataProvider="DPlinkdin")
	public void linkdinLogin(String username,String password)
	{
		driver.get(url);
		SocialLoginPage obj=new SocialLoginPage(driver);
		obj.socialLinkdinLogin(username,password);
		ATUReports.add("After Login",LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		sassert.assertEquals(driver.getTitle(),(prop.getProperty("linkedin_loggedIn_pageTitle")));
		sassert.assertAll();
	}
	
	@DataProvider(name="DPlinkdin")
	  public Object[][] readData4()
	  {   
		  XlsReader reader=new XlsReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\data.xlsx");
		  Object[][] data= reader.dataForDataProvider("linkdin");
		  return data;
	  }
	
	
	@AfterTest
	public void cleanUp()
	{ 
	 try
	   {
		 Thread.sleep(10000);
	   }
	 catch(Exception e)
	   {
		
	   }
	 
	 //Closing all open Windows
	 Set<String> windows = driver.getWindowHandles();
		try 
		{
			for (String child : windows)
				{				
					driver.switchTo().window(child);	
				}
		} 
		catch (Exception e)
		{

			throw new RuntimeException("Exception", e);
		}
		driver.quit();
	}
}
