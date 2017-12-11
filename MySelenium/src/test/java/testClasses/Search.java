package testClasses;
import utils.BrowserFactory;
import org.openqa.selenium.*;
import utils.XlsReader;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import pom.pages.SearchPage;
import pom.utils.PropertyReader;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
public class Search 
{
	WebDriver driver;
	SoftAssert sassert=new SoftAssert();
	Properties prop=PropertyReader.read(System.getProperty("user.dir")+"\\src\\test\\resources\\urls\\url.properties");
	String url=prop.getProperty("searchUrl");
	{
	  System.setProperty("atu.reporter.config",System.getProperty("user.dir")+"\\src\\test\\resources\\atu-jars\\atu.properties");
	}
	@BeforeTest
	  public void beforeTest() 
	  {
		  driver=BrowserFactory.getBrowser();
		  driver.get(url);
		  driver.manage().window().maximize();
		  ATUReports.setWebDriver(driver);		  
		  ATUReports.indexPageDescription = "lorem ipsum";
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
	  }
  @SuppressWarnings("deprecation")
  @Test(dataProvider="DP")
  public void searchTest(String searchKey) 
  {
	  ATUReports.setAuthorInfo("Firoj Khan", "12-Nov-2013 3:46", "1.2");
	  SearchPage obj=new SearchPage(driver);
	  obj.search(searchKey);
	  String bodyText = driver.findElement(By.tagName("body")).getText();
	  if(bodyText.contains("Sorry, but you are looking for something that isn't here."))
	   {
		  ATUReports.add("Search result Page",LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		  ATUReports.add("Search Result",searchKey, "", "Searched Key Not found", true);
	   }
	  else
	  {
		  ATUReports.add("Search result Page",LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		  ATUReports.add("Search Result",searchKey, "", "Searched Key found", true);
	  }
	  try
	  {
	  Thread.sleep(2000);
	  }
	  catch(Exception e)
	  {
		  
	  }
	  
  }
  

  @AfterTest
  public void afterTest() 
  {
	  try
	  {
	  Thread.sleep(3000);
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
	  Object[][] data= reader.dataForDataProvider("search");
	  return data;
  }
}
