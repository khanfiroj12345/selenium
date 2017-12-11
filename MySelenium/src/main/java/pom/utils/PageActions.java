package pom.utils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import java.util.*;
import java.util.concurrent.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActions 
{
	public static void hover(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
	}

	//Drag and drop one element to another
	public static void dragAndDrop(WebDriver driver, WebElement from,WebElement to)
	{
		Actions builder = new Actions(driver);	 
		Action dragAndDrop = builder.clickAndHold(from)	 
		.moveToElement(to)	 
		.release(to)	 
		.build(); 
		dragAndDrop.perform();
	}
	
	//Input in capital
	public static void inputInCaps(WebDriver driver,WebElement element,String input)
	{
		Actions builder = new Actions(driver);
		Action enterText = builder.click(element).
                keyDown(Keys.SHIFT).
                sendKeys(input).
                keyUp(Keys.SHIFT).
                build();
                enterText.perform();
	}
	
	
	//Select a value from dropDown by visible text
	public static void selectDropdownByVisibleText(WebDriver driver, WebElement element, String visibleText)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(element));
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	
	
	//select by Index
	public void selectDropDownByIndex(WebElement element,int index) 
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	/*
	 Select the value from a dropdown list by its value
	 */
	public void selectDropDownByValue(WebElement element,String value) 
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	
	//browser next
	public static void navigateNext(WebDriver driver)
	{
		driver.navigate().forward();
	}
	
	
	//Browser back button
	public static void navigateBack(WebDriver driver)
	{
		driver.navigate().back();
	}
	
	//switch to window provided by title of window
	public static String switchToWindow(WebDriver driver,String childWindowTitle)
	{
		
		String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		try 
		{
			if (windows.size() > 1) 
			{
				for (String child : windows)
				{
					if (!child.equals(parent))
					{
						if (driver.switchTo()
								.window(child).getTitle()
								.equals(childWindowTitle))
						{
							driver.switchTo()
									.window(child);
						}
					}
				}
			}
		} catch (Exception e)
		{

			throw new RuntimeException("Exception", e);
		}
       return parent;
	}
	
	
	//Switch to child window if only 1 child window is there
	public static String switchToChildWindow(WebDriver driver)
	{
		String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		try 
		{
			if (windows.size() > 1) 
			{
				for (String child : windows)
				{
					if (!child.equals(parent))
					{
							driver.switchTo().window(child);	
					}
				}
			}
		} 
		catch (Exception e)
		{

			throw new RuntimeException("Exception", e);
		}
		return parent;
	}
	
	
	//Select particular date in calender, date format(dd/mm/yyyy)
	public static void selectDateFromCalendar(WebDriver driver, String date) 
	{
		String[] data = date.split("/");
		List<String> monthList = Arrays.asList("January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December");
		int expMonth;
		int expYear;
		String expDate = null;
		
		// Calendar Month and Year
		String calMonth = null;
		String calYear = null;
		
		boolean dateNotFound=true;

		// Set your expected date, month and year.
		expDate = data[0];
		expMonth = Integer.parseInt(data[1]);
		expYear = Integer.parseInt(data[2]);

		// This loop will be executed continuously till dateNotFound Is true.
		while (dateNotFound) 
		{
			// Retrieve current selected month name from date picker popup.
			calMonth = driver.findElement(By.className("ui-datepicker-month")).getText();

			// Retrieve current selected year name from date picker popup.
			calYear = driver.findElement(By.className("ui-datepicker-year")).getText();

			/*
			 * If current selected month and year are same as expected month and
			 * year then go Inside this condition.
			 */
			if (monthList.indexOf(calMonth) + 1 == expMonth
					&& (expYear == Integer.parseInt(calYear))) 
			{
				selectDate(driver,expDate);
				dateNotFound = false;
			}
			// If current selected month and year are less than expected month
			// and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 < expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear > Integer.parseInt(calYear))
			{
				// Click on next button of date picker.need to change the xpath
				/*
				 * driver.findElement(
				 * By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a/span"))
				 * .click();
				 */
			}
			// If current selected month and year are greater than expected
			// month and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 > expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear < Integer.parseInt(calYear)) {

				// Click on previous button of date picker.need to change the xpath

				/*
				 * driver.findElement(
				 * By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/a/span"))
				 * .click();
				 */
			}
		}
	}
	
	
	/*
	 Selects the Date
	 */
	public static void selectDate(WebDriver driver,String date) 
	{
		WebElement datePicker = driver.findElement(
				By.id("ui-datepicker-div"));
		List<WebElement> noOfColumns = datePicker
				.findElements(By.tagName("td"));

		// Loop will rotate till expected date not found.
		for (WebElement cell : noOfColumns) 
		{
			// Select the date from date picker when condition match.
			if (cell.getText().equals(date)) 
			{
				cell.findElement(By.linkText(date)).click();
				break;
			}
		}

	}
	
	
	//Clear a Web Element
	public static void clear(WebDriver driver, WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}
	
	
	//click OK on alert
	public static void alertAccept(WebDriver driver)
	{

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());		
		Alert alert = driver.switchTo().alert();		
		alert.accept();
	}
	
	
	//Click cancel on alert 
	public static void alertCancel(WebDriver driver)
	{

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());		
		Alert alert = driver.switchTo().alert();		
		alert.accept();
	}
	
	//Scroll any element into view
	public static void scrollElementIntoView(WebDriver driver,WebElement element) 
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	//Sleep thread by "i" milliseconds
	public static int wait1(long i) 
	{
		try 
		{
			Thread.sleep(i);
		} 
		catch (InterruptedException e) 
		{
			System.out.println(e.toString());
		}
		return 1;
	}
	
}
