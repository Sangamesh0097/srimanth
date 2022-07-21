package com.crm.Vtiger.Vendors;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrearingVendorsAndCreateProductAndAddVendorsNameIntoProductTest {

	public static void main(String[] args) throws InterruptedException, IOException {
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

		

		//create actions object for mouse operating perpose
		Actions actions=new Actions(driver);

		//mouse over more option
		actions.moveToElement(driver.findElement(By.xpath("//a[.='More']"))).perform();

		//click on vendors
		driver.findElement(By.name("Vendors")).click();
		
		//click on create vendors
		driver.findElement(By.xpath("//img[@title=\"Create Vendor...\"]")).click();
		
		//enter vendors name
		driver.findElement(By.name("vendorname")).sendKeys("Pramodh");
		
		//enter vendors email id
		driver.findElement(By.id("email")).sendKeys("sangamesh0097@gmail.com");
		
		//click on save button
		driver.findElement(By.name("button")).click();
		
		//click on vendors
		driver.findElement(By.xpath("//a[.='Products']")).click();
		
		//click on create vendors
		driver.findElement(By.cssSelector("img[title='Create Product...']")).click();
		
		//enter product name
		driver.findElement(By.name("productname")).sendKeys("Laptop");
		
		//click on add vendors button
		driver.findElement(By.xpath("//img[@title=\"Select\"]")).click();
		
		//get all windows id's
		Set<String> allWindowIds = driver.getWindowHandles();
	
		//handle new window
		for (String windowid : allWindowIds) {
			 
			String windowTitle = driver.switchTo().window(windowid).getTitle();
			 System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("Pramodh");
				
				//click on search
				driver.findElement(By.name("search")).click();
				
				//click on vendors
				driver.findElement(By.xpath("//a[.='Pramodh']")).click();
				driver.switchTo().window(windowTitle);
				break;
			}
		}
		
		//click on save button
		driver.findElement(By.name("button")).click();
		System.out.println("Vendor is created and product is also created ");
		
		//click on adminstrater
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
		
		//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		//terminate the section
		driver.quit();
	
	}
	
}
