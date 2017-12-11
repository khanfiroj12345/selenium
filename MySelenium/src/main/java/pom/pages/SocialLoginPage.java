package pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import pom.customFactory.FileBasedElementLocatorFactory;
import pom.customFactory.SearchWith;
import pom.utils.*;

public class SocialLoginPage 
{
	 WebDriver driver;
	 String frame_name="OneAll Social Login";
	
	    /*@seachWith is custom annotation implemented to read locators from property File, it accepts Property
	    File name, accessor type(only 3 types-xpath, Id, name)*/
	    
	 
	 // Gmail WebElements
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="gmail_username")
	    WebElement GmailUserName;
	    
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="gmail_next_button")
	    WebElement GmailNextButton;
	    
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="gmail_password")
	    WebElement GmailPassword;
	    
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="gmail_submit")
	    WebElement GmailSubmit;
	   
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="gmail_icon")
	    WebElement GmailIcon;
	    
	    
	    //facebook WebElements
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="fb_username")
	    WebElement FbUserName;
	
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="fb_password")
	    WebElement FbPassword;
	    
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="fb_submit")
	    WebElement FbSubmit;
	   
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="fb_icon")
	    WebElement FbIcon; 
	    
	    
	  //Twitter WebElements
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="twit_username")
	    WebElement TwitUserName;
	
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="twit_password")
	    WebElement TwitPassword;
	    
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="twit_submit")
	    WebElement TwitSubmit;
	   
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="twit_icon")
	    WebElement TwitIcon;
	    
	  //Linkdin WebElements
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="linkedin_username")
	    WebElement LinkdinUserName;
	
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="linkedin_password")
	    WebElement LinkdinPassword;
	    
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="linkedin_submit")
	    WebElement LinkdinSubmit;
	   
	    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\socialLoginOR.properties",xpath="linkedin_icon")
	    WebElement LinkdinIcon;
	    
	    public SocialLoginPage(WebDriver driver)
        {
            this.driver=driver;
            
             /*These 2 statements are necessary to use custom factory  */
             ElementLocatorFactory factory = new FileBasedElementLocatorFactory(driver);
        	 PageFactory.initElements(factory,this);
        }
	    
	   
	     public void socialGmailLogin(String userName,String password)
	        {  
	    	   //switch frame
	    	   driver.switchTo().frame(frame_name);
	    	   GmailIcon.click();
	    	   //switching to child window
	    	   String parent=PageActions.switchToChildWindow(driver);
	           //Fill user name
	    	   GmailUserName.sendKeys(userName);
	           //Click Next button
	    	   GmailNextButton.click();
	           //Fill password
	    	   GmailPassword.sendKeys(password);
	           //Click Login button
	    	   GmailSubmit.click(); 
	    	   try
	    	   {
	    		 Thread.sleep(5000);
	    	   }
	    	 catch(Exception e)
	    	   {
	    		 
	    	   }
	    	   driver.switchTo().window(parent);
	        }
	     
	     
	     public void socialFacebookLogin(String userName,String password)
	        {  
	    	   //switch frame
	    	   driver.switchTo().frame(frame_name);
	    	   FbIcon.click();
	    	   String parent=PageActions.switchToChildWindow(driver);
	           //Fill user name
	    	   FbUserName.sendKeys(userName);
	           //Fill password
	    	   FbPassword.sendKeys(password);
	           //Click Login button
	    	   FbSubmit.click(); 
	    	   try
	    	   {
	    		 Thread.sleep(5000);
	    	   }
	    	 catch(Exception e)
	    	   {
	    		 
	    	   }
	    	   //switching back to parent window
	    	   driver.switchTo().window(parent);
	        }
	     
	     
	     public void socialTwitterLogin(String userName,String password)
	        {  
	    	   //switch frame
	    	   driver.switchTo().frame(frame_name);
	    	   TwitIcon.click();
	    	   String parent=PageActions.switchToChildWindow(driver);
	           //Fill user name
	    	   TwitUserName.sendKeys(userName);
	           //Fill password
	    	   TwitPassword.sendKeys(password);
	           //Click Login button
	    	   TwitSubmit.click(); 
	    	   try
	    	   {
	    		 Thread.sleep(5000);
	    	   }
	    	   catch(Exception e)
	    	   {
	    		   System.out.println(e.toString());
	    	   }
	    	   //Switching back to parent window
	    	   driver.switchTo().window(parent);
	        }
	     
	     public void socialLinkdinLogin(String userName,String password)
	        {  
	    	   //switch frame
	    	   driver.switchTo().frame(frame_name);
	    	   LinkdinIcon.click();
	    	   String parent=PageActions.switchToChildWindow(driver);
	           //Fill user name
	    	   LinkdinUserName.sendKeys(userName);
	           //Fill password
	    	   LinkdinPassword.sendKeys(password);
	           //Click Login button
	    	   LinkdinSubmit.click(); 
	    	   try
	    	   {
	    		 Thread.sleep(5000);
	    	   }
	    	   catch(Exception e)
	    	   {
	    		   System.out.println(e.toString());
	    	   }
	    	   //Switching back to parent window
	    	   driver.switchTo().window(parent);
	        }

}
