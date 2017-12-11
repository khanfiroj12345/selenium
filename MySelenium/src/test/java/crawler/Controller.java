package crawler;
import java.util.Date;
import java.text.SimpleDateFormat;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.util.Properties;
import pom.utils.PropertyReader;
import utils.XlsReader;

import org.testng.annotations.*;
public class Controller 
{
	public static String filePath;
	@BeforeTest
	public void createOutputFile()
	{
		//Creating output File appended with timeStamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		filePath =System.getProperty("user.dir")+"\\Link_Checker_Results_"+timeStamp+".xlsx";
		XlsReader.createExcelFile(filePath);
	}
	
	@Test
	public void test()
	{
		try
		{			
		  XlsReader outputFile=new XlsReader (filePath);
		  
		  //Added column to Excel file
		  outputFile.addColumn("Sheet1","Page Url");
		  outputFile.addColumn("Sheet1","Link visible Text");
	      outputFile.addColumn("Sheet1","Link URL");
	      outputFile.addColumn("Sheet1","Link Status");
	      
	      //Set user Defined directory for Temporary data of Crawler
          String crawlStorageFolder = System.getProperty("user.dir")+"\\src\\test\\resources\\crawler_resources";
          
          //Define number of Parallel threads to crawl
          int numberOfCrawlers =1;
          
          //Configure crawler
          CrawlConfig config = new CrawlConfig();
          //Set Storage folder
          config.setCrawlStorageFolder(crawlStorageFolder);
          //Set the delay between requests send to target website
          config.setPolitenessDelay(0);
          //Depth of crawler to which it will fetch Urls
          config.setMaxDepthOfCrawling(2);
          //Limit on Pages
          config.setMaxPagesToFetch(1000);
          config.setIncludeBinaryContentInCrawling(false);
          //Resume interrupted Crawling
          config.setResumableCrawling(false);

        /*
         Instantiate the controller for this crawl.
         */
          PageFetcher pageFetcher = new PageFetcher(config);
          RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
          RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
          CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         For each crawl, you need to add some seed urls. These are the first
         URLs that are fetched and then the crawler starts following links
         which are found in these pages
         */
          
          //Read Base Url from config.properties file
          Properties prop=PropertyReader.read("\\src\\test\\resources\\config\\config.properties");         
          controller.addSeed(prop.getProperty("baseUrl"));          
          controller.start(MyCrawler.class, numberOfCrawlers);
	   }
        
        catch(Exception e)
		{
			e.printStackTrace();
		}
    }

}
