package pom.pages;

import pom.customFactory.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class LoginPage 
{
    WebDriver driver;
    
    /*@seachWith is custom annotation implemented to read locators from property File, it accepts Property
    File name, accessor type(only 3 types-xpath, Id, name)*/
    
    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\loginOR.properties",xpath="username")
    WebElement UserName;
    
    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\loginOR.properties",xpath="password")
    WebElement Password;
    
    @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\loginOR.properties",xpath="submit")
    WebElement Login;
    
    
    public LoginPage(WebDriver driver)
         {
             this.driver=driver;
             
             /*These 2 statements are necessary to use custom factory  */
             ElementLocatorFactory factory = new FileBasedElementLocatorFactory(driver);
        	 PageFactory.initElements(factory,this);
         }
   
    
    //Set user name in text-box
    public void setUserName(String userName)
        { 
             UserName.sendKeys(userName);
        }


    //Set password in password text-box
    public void setPassword(String password)
        {
             Password.sendKeys(password);
        }


     //Click on login button
    public void clickLogin()
        {
             Login.click();
        }
    
    
     public void login(String userName,String password)
        {
          //Fill user name
           setUserName(userName);
           //Fill password
           setPassword(password);
           //Click Login button
           clickLogin();        
        }
}
