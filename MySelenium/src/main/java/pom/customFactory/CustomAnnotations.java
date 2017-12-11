package pom.customFactory;

import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import pom.customFactory.SearchWith;
 
import java.lang.reflect.Field;
import java.util.Properties;
 
class CustomAnnotations extends AbstractAnnotations 
{
    private final Field field;
 
    CustomAnnotations(Field field) 
    {
        this.field = field;
    }
 
    //method to override the way to read an element locator
    //here we are reading element from property file, we can implemented the customized way here
    
    @Override
    public By buildBy() 
    {
        SearchWith search = field.getAnnotation(SearchWith.class);
        Preconditions.checkArgument(search != null, "Failed to locate the annotation @SearchWith");
        
        //get user data from annotation
        String elementName = search.name();
        String elementId=search.id();
        String elementXpath=search.xpath();
        String locatorsFile = System.getProperty("user.dir")+(search.locatorsFile());
        Preconditions.checkArgument(isNotNullAndEmpty(locatorsFile), "Locators File name not provided");
        Preconditions.checkArgument(isNotNullAndEmpty(elementName)||isNotNullAndEmpty(elementId)||isNotNullAndEmpty(elementXpath), "No locator found");
        Properties prop=PropertyReader.read(locatorsFile);
        By by=null;
        
        //return By type variable based on user passed locator from property file
        if(!elementXpath.isEmpty())
        {
           try 
             {
               String locator = prop.getProperty(elementXpath);
               by=new By.ByXPath(locator);
             } 
           catch (Exception e)
             {
               throw new RuntimeException(e);
             }
        }
        else if(!elementId.isEmpty())
        {
           try 
             {
               String locator = prop.getProperty(elementId);
               by=new By.ById(locator);
             } 
           catch (Exception e)
             {
               throw new RuntimeException(e);
             }
        }
        else if(!elementName.isEmpty())
        {
           try 
             {
               String locator = prop.getProperty(elementName);
               by=new By.ByName(locator);
             } 
           catch (Exception e)
             {
               throw new RuntimeException(e);
             }
        }
        return by;
    }
 
    //lookup is cached or not
    @Override
    public boolean isLookupCached() 
    {
        return (field.getAnnotation(CacheLookup.class) != null);
    }
 
    private boolean isNotNullAndEmpty(String arg)
    {
        return ((arg != null) && (! arg.trim().isEmpty()));
    }
}
