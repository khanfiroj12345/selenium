package pom.pages;

import pom.customFactory.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class SearchPage 
{
	WebDriver driver;
	 @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\searchOR.properties",xpath="searchbox")
	 WebElement SearchBox;
	 
	 @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\searchOR.properties",xpath="submit1")
	 WebElement Submit1;
	 
	 @SearchWith(locatorsFile="\\src\\test\\resources\\OR\\searchOR.properties",xpath="submit2")
	 WebElement Submit2;
	 
	 public SearchPage(WebDriver driver)
	 {
		 
		 this.driver=driver;
		 ElementLocatorFactory factory = new FileBasedElementLocatorFactory(driver);
		 PageFactory.initElements(factory,this);
	 }
	 
	 public void search(String searchKey)
	   {
		 Submit1.click();
		 SearchBox.sendKeys(searchKey);
		 Submit2.click();
	   }
}
