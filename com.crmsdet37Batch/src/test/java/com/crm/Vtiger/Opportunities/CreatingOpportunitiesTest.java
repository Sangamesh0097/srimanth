package com.crm.Vtiger.Opportunities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatingOpportunitiesTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				
		WebDriver driver=null;
		
		//Fetch the data from properties file(External file)
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommunData.properties");
		Properties property=new Properties();
		property.load(fis);
		
		String URL = property.getProperty("url");
		String USERNAME = property.getProperty("username");
		String PASSWORD = property.getProperty("password");
		String BROWSER = property.getProperty("browser");
		System.out.println(BROWSER);

		//Execute chrome Browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
			
		}
		//Execute Firefox Browser
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
		}
		//Execute msedge Browser
		else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
		}
		
		///Execute opera Browser
		else if (BROWSER.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver =new OperaDriver();
		}
		
		//Execute safari Browser
		else if (BROWSER.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver =new SafariDriver();
		}
		
		else {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
		}
		

		//maximize the window
		driver.manage().window().maximize();

		//create implicitly wait for 10sec
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//create explicitly wait for 10sec
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));

		//Enter url of application 
		driver.get(URL);

		//Enter username into username text field
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);

		//Enter password into password text field
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

		//Click on Login button
		driver.findElement(By.id("submitButton")).click();
		
		//click on Opportunities	
		driver.findElement(By.xpath("//td[10]//a[.='Opportunities']")).click();
		
		//click on Create Opportunities
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		
		//enter Opportunity Name
		driver.findElement(By.name("potentialname")).sendKeys("Best process");
		
		WebElement dropDown = driver.findElement(By.name("related_to_type"));
		
		//Handling dropdown
		Select select=new Select(dropDown);
		select.selectByValue("Contacts");
		
		//click on contact option
		driver.findElement(By.xpath("//tr[4]//td[2]//img[@src='themes/softed/images/select.gif']")).click();
		
		//parent window id
		String parentWindow = driver.getWindowHandle();
		
		//get all window id's
		Set<String> allWindowIds = driver.getWindowHandles();
		
		//handled diffrent windows
		for (String windowid : allWindowIds) {
			
			 String windowTitle = driver.switchTo().window(windowid).getTitle();
			 System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {

				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("kumar");
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='Sanjay kumar']")).click();
				break;
			}		
		}
		
		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);
		
		//enter amount
		driver.findElement(By.name("amount")).sendKeys("50000");
		
		//handled calender popup
		WebElement calender = driver.findElement(By.xpath("//input[@name=\"closingdate\"]"));
		calender.clear();
		calender.sendKeys("2022-07-15");
		
		//click the create compaign link
		driver.findElement(By.xpath("//tr[8]//img[@src=\"themes/softed/images/select.gif\"]")).click();
		
		//handled diffrent windows
		String parentWindow1 = driver.getWindowHandle();
		Set<String> allWindowIds1 = driver.getWindowHandles();
		for (String windowid1 : allWindowIds1) {
			 String windowTitle = driver.switchTo().window(windowid1).getTitle();
			 System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {

				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("mnc");
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='mnc']")).click();
				break;
			}		
		}
		driver.switchTo().window(parentWindow1);
		//click on save button
		driver.findElement(By.name("button")).click();
		
		//verify opportunity creted or not
		String expected="Sanjay kumar";
		String actual = driver.findElement(By.xpath("//a[@title=\"Contacts\"]")).getText();
		
		if (expected.contains(actual)) {
			System.out.println("Created page is verified and opportunity is created");
			
		}
		else {
			System.out.println("Created page is verified and opportunity is not created");
		}
		
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
		//SignOut the vtiger
     	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
     	driver.quit();
	}

}
