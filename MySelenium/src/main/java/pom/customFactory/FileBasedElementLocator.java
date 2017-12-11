package pom.customFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
 
import java.util.List;
 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

//implement Element Locator
public class FileBasedElementLocator implements ElementLocator
{
 
    private final SearchContext searchContext;
    private final boolean shouldCache;
    private final By by;
    private WebElement cachedElement;
    private List<WebElement> cachedElementList;
 
 
    public FileBasedElementLocator(SearchContext searchContext, AbstractAnnotations annotations)
    {
        this.searchContext = searchContext;
        this.shouldCache = annotations.isLookupCached();
        this.by = annotations.buildBy();
    }
 
    //find element using By returned from buildBy 
    @Override
    public WebElement findElement() 
    {
    	WebElement element=null;
    	
    	//returned cached WebElement if element already found
        if (cachedElement != null && shouldCache) 
        {
            return cachedElement;
        }
        
        try
        {
        	//Explicitly waiting for element to be clickable
           WebDriverWait wait = new WebDriverWait((WebDriver)searchContext, 30);
           element = wait.until(ExpectedConditions.elementToBeClickable(by));
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
        }
        if (shouldCache) 
        {
            cachedElement = element;
        }
        return element;
 
    }
 
    //find multiple elements
    @Override
    public List<WebElement> findElements() 
    {
        if (cachedElementList != null && shouldCache) 
        {
            return cachedElementList;
        }
 
        List<WebElement> elements = searchContext.findElements(by);
        if (shouldCache) 
        {
            cachedElementList = elements;
        }
 
        return elements;
 
    }
}

