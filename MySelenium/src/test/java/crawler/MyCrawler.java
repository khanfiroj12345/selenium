package crawler;
import java.util.Properties;
import java.util.regex.Pattern;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import pom.utils.PropertyReader;
import utils.BrowserFactory;

import org.openqa.selenium.*;

public class MyCrawler extends WebCrawler
{
	public static WebDriver driver=BrowserFactory.getBrowser();
	
	//Define pattern to Exclude the Urls from visiting
   private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");
   @Override
   
   //Restrict the pages that our crawler should visit
   public boolean shouldVisit(Page referringPage, WebURL url)
   {
	  Properties prop=PropertyReader.read("\\src\\test\\resources\\config\\config.properties");          
      String href = url.getURL().toLowerCase();
      return !FILTERS.matcher(href).matches()&& href.startsWith(prop.getProperty("limitCrawlerToDomain"));
   }

   
   //Visit the page and Check for all links present on the page
   @Override
   public void visit(Page page) 
   {
      String url = page.getWebURL().getURL();
      if (page.getParseData() instanceof HtmlParseData) 
         {
    	  LinkChecker.ShowBrokenLinks(driver,url,Controller.filePath); 
         }
   }
}
