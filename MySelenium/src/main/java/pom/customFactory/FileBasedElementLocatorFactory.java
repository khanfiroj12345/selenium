package pom.customFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
 
import java.lang.reflect.Field;
 
public class FileBasedElementLocatorFactory implements ElementLocatorFactory
{
    private final SearchContext searchContext;
    //receive search-context refrence 
    public FileBasedElementLocatorFactory(SearchContext searchContext) 
    {
        this.searchContext = searchContext;
    }
 
    //use this function to call customised Element locator Class
    @Override
    public ElementLocator createLocator(Field field) 
    {
        return new FileBasedElementLocator(searchContext, new CustomAnnotations(field));
    }
}
