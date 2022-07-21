package com.crm.Vtiger.SalesOrder;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

public class CreatingSalesOrderTest {

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

		//create actions object for mouse operating perpose
		Actions actions=new Actions(driver);

		//mouse over more option
		actions.moveToElement(driver.findElement(By.xpath("//a[.='More']"))).perform();

		//click on sales order
		driver.findElement(By.name("Sales Order")).click();

		//click on create sales order
		driver.findElement(By.xpath("//img[@title='Create Sales Order...']")).click();

		Random random = new Random();
		int randomNumber = random.nextInt(100);
		//Fetch the data from the excel Sheet
		FileInputStream fileInputStream2 = new FileInputStream("./src/test/resources/Excel.xlsx");
				
		//Fetch subject name from Excel
		Row row = WorkbookFactory.create(fileInputStream2).getSheet("Vtiger").getRow(2);
		String value = row.getCell(12).toString();
		String subject = value+randomNumber;
		
		//enter subject name
		driver.findElement(By.name("subject")).sendKeys(subject);

		//Fetch subject name from Excel
		Row row1 = WorkbookFactory.create(fileInputStream2).getSheet("Vtiger").getRow(1);
		String contactNo= row1.getCell(12).toString();
		driver.findElement(By.name("customerno")).sendKeys(contactNo);

		//click on create contack
		driver.findElement(By.xpath("//tr[6]//td[2]//img[@src='themes/softed/images/select.gif']")).click();

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
				driver.switchTo().alert().accept();

				break;
			}		
		}

		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);

		//find dropdown 
		WebElement dropDown = driver.findElement(By.name("sostatus"));


		Select select=new Select(dropDown);

		//select approved option in dropdown
		select.selectByValue("Approved");

		//click on create opportunity link
		driver.findElement(By.xpath("//tr[3]//td[4]//img[@src='themes/softed/images/select.gif']")).click();

		//get parent window id
		String parentWindow1 = driver.getWindowHandle();

		//get all windows id 
		Set<String> allWindowIds1 = driver.getWindowHandles();

		//handled differance window
		for (String windowid1 : allWindowIds1) {
			String windowTitle = driver.switchTo().window(windowid1).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("Best process");
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='Best process']")).click();
				//driver.switchTo().alert().accept();
				break;
			}		
		}
		
		//driver control switch back to parent window
		driver.switchTo().window(parentWindow1);
		
		//send date to calender
		driver.findElement(By.name("duedate")).sendKeys("2022-07-15");
		
		//click on create organization name
		driver.findElement(By.xpath("//tr[9]//td[4]//img[@src='themes/softed/images/select.gif']")).click();

		//get parent window id
		String parentWindow2 = driver.getWindowHandle();
		
		//get all windows id 
		Set<String> allWindowIds2 = driver.getWindowHandles();
		
		//handled differance window
		for (String windowid2 : allWindowIds2) {
			String windowTitle = driver.switchTo().window(windowid2).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("TestYantra software solution pvt ltd457");
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='TestYantra software solution pvt ltd457']")).click();
				driver.switchTo().alert().accept();
				break;
			}		
		}
		
		//driver control switch back to parent window
		driver.switchTo().window(parentWindow2);
		
		//enter billing address
		driver.findElement(By.name("bill_street")).sendKeys("Near basavangudi bangalore");
		
		//enter shipping address
		driver.findElement(By.name("ship_street")).sendKeys("Near shivaji chowk mumbai");
		
		//click on product link
		driver.findElement(By.id("searchIcon1")).click();

		//get parent window id
		String parentWindow3 = driver.getWindowHandle();
		
		//get all windows ids
		Set<String> allWindowIds3 = driver.getWindowHandles();
		
		//handled diffrent window
		for (String windowid3 : allWindowIds3) {
			String windowTitle = driver.switchTo().window(windowid3).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("Laptop");
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='Laptop']")).click();
				break;
			}		
		}
		
		//driver control switch back to parent window
		driver.switchTo().window(parentWindow3);
		
		//enter quantity
		driver.findElement(By.id("qty1")).sendKeys("25");
		
		WebElement amount = driver.findElement(By.id("listPrice1"));
		amount.clear();
		
		//enter price
		amount.sendKeys("50000");
		
		//click on save button
		driver.findElement(By.name("button")).click();

		//expected result
		String expected=subject;
		
		//actual result
		String actual = driver.getPageSource();

		//verify sales order created or not
		if (actual.contains(expected)) {
			System.out.println("Sales order page is verified and Sales order is created");

		}
		else {
			System.out.println("Sales order page is verified and Sales order is not created");
		}

		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();

		//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();


	}

}
