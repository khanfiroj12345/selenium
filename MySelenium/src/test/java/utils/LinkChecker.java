package utils;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;


public class LinkChecker 
 {
	/*need driver refrence, page url on which we have to perform link checking, 
	 * excelFile path and sheet Name where result will be written*/
	public static void ShowBrokenLinks(WebDriver driver,String pageUrl,String excelFilePath,String sheetName)
	{
		   driver.get(pageUrl); 
	       driver.manage().window().maximize();
	       XlsReader outputFile=new XlsReader (excelFilePath);
	       
	       //added 2 columns to sheet
	       outputFile.addColumn(sheetName,"Link Text of URL");
	       outputFile.addColumn(sheetName,"Status of link");
	       int rowNum=1;
	       try
	         {
	    	   //store all Anchor Tags 
	    	   List <WebElement> linksarr=driver.findElements(By.tagName("a"));    	   
	    	   for(WebElement e:linksarr)
	    	   {
	    		   String url=e.getAttribute("href");
	    		   String urlText=e.getText();
	    		   if(url==null)
	    	 	     {
	    			   System.out.println(e.getText()+ "Link is set as Blank");
	    		     }
	    		   
	    		   // Create and send http request for all collected url from page
	    		   else if(!url.contains("javascript"))
	    		   {
	    			   HttpClient client = HttpClientBuilder.create().build();
	    			   HttpGet request = new HttpGet(url);
	    			   HttpResponse response = client.execute(request);
	    			   
	    			   //check the response code of http request for validity of url
	    			   if (response.getStatusLine().getStatusCode() != 200)
	    			   {
	    				   //write the status of link to excel file
	    				   System.out.println(urlText+" :URL is broken");
	    				   outputFile.setCellData(sheetName,"Link Text of URL",rowNum,urlText);
	    				   outputFile.setCellData(sheetName,"Status of link",rowNum++,"URL is broken");
	    			   }
	    			   else
	    			   {
	    				 //write the status of link to excel file
	    				   System.out.println(urlText+" :URL is working fine");
	    				   outputFile.setCellData(sheetName,"Link Text of URL",rowNum,urlText);
	    				   outputFile.setCellData(sheetName,"Status of link",rowNum++,"URL is working fine");
	    			   }
	    		   }	    		   	    		   
	    	   }	    	   	    	   
	         }
	       
	       catch (Exception e){}
	       driver.close();
	  }
}
 