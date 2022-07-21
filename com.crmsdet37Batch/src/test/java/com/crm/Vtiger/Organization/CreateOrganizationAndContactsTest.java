package com.crm.Vtiger.Organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationAndContactsTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//creating random number perpuse   
		Random random=new Random();
		int randomNum = random.nextInt(1000);
		
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

		//Click on Organization link
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		
		//Click on create organization link
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Enter organization name into organization name text field
		driver.findElement(By.name("accountname")).sendKeys("TestYantra software solution pvt ltd"+randomNum);		
		
		//select Technology option in dropdown
		WebElement dropDown = driver.findElement(By.name("industry"));
		Select select=new Select(dropDown);
		select.selectByValue("Technology");
		
		//select Analyst option in dropdown
		WebElement dropDown1 = driver.findElement(By.name("accounttype"));
		Select select1=new Select(dropDown1);
		select1.selectByIndex(1);
		
		//click on save button
		driver.findElement(By.name("button")).click();
		Thread.sleep(3000);
				
		boolean flag = false;
		while (false==flag) {
			//click on contacts link
			WebElement contacts = driver.findElement(By.xpath("//td[8]//a[.='Contacts']"));
			contacts.click();
			break;
		}

		//click on create contacts link
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//select mr. option in dropdown
		WebElement dropDown2 = driver.findElement(By.name("salutationtype"));
		Select select2=new Select(dropDown2);
		select2.selectByValue("Mr.");
		
		//enter first name
		driver.findElement(By.name("firstname")).sendKeys("Sanjay");
		
		//enter last name
		driver.findElement(By.name("lastname")).sendKeys("kumar");
		
		//click on organization name link
		driver.findElement(By.xpath("//input[@name='account_name']/..//img[@title=\"Select\"]")).click();
		
		//get all windows id's
		String actualWindowId = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		allWindow.remove(actualWindowId);
		System.out.println(allWindow);
		
		//handle new window
		String elementName="TestYantra software solution pvt ltd00";
		String elementTitle="http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=";
		String actualCreatedPageTitle="";
		for (String window : allWindow) {
			String newWindow = driver.switchTo().window(window).getCurrentUrl();

			if (newWindow.equals(elementTitle)) {
				List<WebElement> allProjects = driver.findElements(By.xpath("//tr[@class=\"lvtColData\"]"));

				for (WebElement projectName : allProjects) {
					if (projectName.getText().contains(elementName)) {
						projectName.click();

					}

				}


			}
			driver.close();
			
			//driver control switch back to main window
			driver.switchTo().window(actualWindowId);
			
			//click on save button
			driver.findElement(By.name("button")).click();
		}
		
		//verify project created or not
		String createdPageTitle = driver.getTitle();
		if (actualCreatedPageTitle.contains(createdPageTitle)) {
			System.out.println("Project is created");
		}
		else {
			System.out.println("Project is not created");
		}
		
		//click on administration
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
		
		//click on sign out 
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		//terminate session
		driver.quit();
	}
}
