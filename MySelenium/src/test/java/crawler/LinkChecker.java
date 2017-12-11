package crawler;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;

import utils.XlsReader;


public class LinkChecker 
 {
	
	//Accepts a Url, check all links on the page , write broken one into Excel file
	public static void ShowBrokenLinks(WebDriver driver,String pageUrl,String excelFilePath)
	{		   
		   driver.get(pageUrl); 		   
	       driver.manage().window().maximize();
	       String sheetName="Sheet1";
	       XlsReader outputFile=new XlsReader (excelFilePath); 
	       int rowNum=outputFile.getRowCount(sheetName);
	    	   List <WebElement> linksarr=driver.findElements(By.tagName("a")); 
	    	   for(WebElement e:linksarr)
	    	   {
	    		   String url=e.getAttribute("href");
	    		   String urlText=e.getText();
	    		   if(url==null||url.isEmpty())
	    	 	     {
	    			   
	    			   ++rowNum;
    				   System.out.println(urlText+" :Blank");
    				   outputFile.setCellData(sheetName,"Page Url",rowNum,pageUrl);
    				   outputFile.setCellData(sheetName,"Link visible Text",rowNum,urlText);
    				   outputFile.setCellData(sheetName,"Link URL",rowNum,url);
    				   outputFile.setCellData(sheetName,"Link Status",rowNum,"Blank");
	    		     }
	    		   else if(!url.contains("javascript")&&!url.contains("mailto"))
	    		   {
	    			   try
	    			   {
	    			     HttpClient client = HttpClientBuilder.create().build();
	    			     HttpGet request = new HttpGet(url);
	    			     HttpResponse response = client.execute(request);
	    			     if (response.getStatusLine().getStatusCode() != 200)
	    			        {
	    				      ++rowNum;
	    				      System.out.println(urlText+" :Broken");
	    				      outputFile.setCellData(sheetName,"Page Url",rowNum,pageUrl);
	    				      outputFile.setCellData(sheetName,"Link visible Text",rowNum,urlText);
	    				      outputFile.setCellData(sheetName,"Link URL",rowNum,url);
	    				      outputFile.setCellData(sheetName,"Link Status",rowNum,"Broken");
	    			        }
	    			   }
	    			   catch (Exception x)
	    		       {
	    		    	   x.printStackTrace();
	    		       }
	    			   
	    		   }	    		   	    		   
	    	   }	    	   	    	   	         	       	       
	  }
}
 