package com.crm.Vtiger.Compaigns;

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

public class CreatingCompaignsAddProductIntoCompaignstTest {

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
		
		//Execute opera Browser
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

		//click on Campaigns
		driver.findElement(By.name("Campaigns")).click();
		
		//click on create Campaigns
        driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
        //enter Campaignsname
		driver.findElement(By.name("campaignname")).sendKeys("Sales");
		
		driver.findElement(By.xpath("//img[@alt=\"Select\"]")).click();
		
		//get all window id's
		Set<String> allWindowIds = driver.getWindowHandles();
		
		//handled diffrent windows
		for (String windowid : allWindowIds) {
			 String windowTitle = driver.switchTo().window(windowid).getTitle();
			 System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("Qsider123");
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='Laptop']")).click();
				driver.switchTo().window(windowTitle);
				break;
			}
		}
		
		//click on save button
		driver.findElement(By.name("button")).click();
		System.out.println("Compaigns is created and Product is added to compaigns");
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
		//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		//terminate the section
		driver.quit();
	}

}
