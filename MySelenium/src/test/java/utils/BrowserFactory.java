package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/*import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.BrowserType;
*/


public class BrowserFactory 
{
 // get Headless browser without GUI
	public static HtmlUnitDriver getHeadlessBrowser()
	{
		HtmlUnitDriver driver=new HtmlUnitDriver();
		return driver;
	}
 
	
//get browser if browser name is passed	
	public static WebDriver getBrowser(String browserName) 
	{
		WebDriver driver = null;
 
		switch (browserName) 
		{
		
		case "Firefox":
			if (driver == null) 
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
			}
			break;
		case "IE":
			
			if (driver == null) 
			{
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\iedriver.exe");
				driver = new InternetExplorerDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			break;
		case "Chrome":
			
			if (driver == null) 
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
			}
			break;
		}
		return driver;
	}
	
	// If browser name is not passed it will pick it from Config.properties
	public static WebDriver getBrowser() 
	{
		WebDriver driver = null;
		String browserName=null;
		Properties prop = new Properties();
		try
		{
		 FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");
		 prop.load(fs);
		 browserName=prop.getProperty("defaultBrowser");
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		switch (browserName) 
		{
		
		case "Firefox":
			
			if (driver == null) 
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				
			}
			break;
		case "IE":
			
			if (driver == null) 
			{
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\iedriver.exe");
				driver = new InternetExplorerDriver();
				
			}
			break;
		case "Chrome":
			
			if (driver == null) 
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}
			break;
		}
		return driver;
	}
	
	
	/*public static  WebDriver getAndroidBrowser() throws MalformedURLException, InterruptedException, FileNotFoundException
	{ 
		WebDriver driver;
		// Create object of  DesiredCapabilities class and specify android platform
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		 
		 
		// set the capability to execute test in chrome browser
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		 
		// set the capability to execute our test in Android Platform
		  capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
		 
		// we need to define platform name
		 capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		 
		// Set the device name as well (you can give any name)
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"lge-lg_h631");
		 
		// set the android version as well 
		  capabilities.setCapability(MobileCapabilityType.VERSION,"5.1.1");
		 
		// Create object of URL class and specify the appium server address
		URL url= new URL(" http://127.0.0.1:4723/wd/hub");
		 
		// Create object of  AndroidDriver class and pass the url and capability that we created
		return (driver = new AndroidDriver(url, capabilities));
	}*/
	
}
