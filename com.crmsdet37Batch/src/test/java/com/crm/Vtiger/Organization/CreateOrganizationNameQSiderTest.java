package com.crm.Vtiger.Organization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationNameQSiderTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver=null;
		
		//Fetch the data from properties file(External file)
		FileInputStream fileinputstream1=new FileInputStream(".\\src\\test\\resources\\CommunData.properties");
		Properties property=new Properties();
		property.load(fileinputstream1);
		
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
		
		 
		 //creating random number performance
		 Random number=new Random();
         int random = number.nextInt(1000);
		
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
		
		//Fetch the data from the excel Sheet
		FileInputStream fileInputStream2 = new FileInputStream("./src/test/resources/Excel.xlsx");
		
		//Fetch Organization name from Excel
		Row row = WorkbookFactory.create(fileInputStream2).getSheet("Vtiger").getRow(2);
		String value = row.getCell(2).toString();
		String organizationName = value+random;
		
		//Enter organization name into organization name text field
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		
		//click on save button
		driver.findElement(By.name("button")).click();
		
		//Expected organization name in organization created page
		String expected=organizationName;
		
		//Actual organization name in organization created page
		String actual = driver.findElement(By.id("dtlview_Organization Name")).getText();

		//Verifing Organization created or not
		if (actual.contains(expected)) {
			System.out.println("Organization page is verified and Organization is created ");
			
		}
		else {
			System.out.println("Organization page is verified and Organization is not created ");
		}
		
		
	/*	//Fetch Data from Excel sheet		
		FileInputStream fis123=new FileInputStream(".\\src\\test\\resources\\Vtiger.xlsx");
		Workbook workbook = WorkbookFactory.create(fis123);
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(2);
		String value = cell.getStringCellValue();
		System.out.println(value);*/
		
		//click on administrater
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
		
		//click on SignOut link
     	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
     	
     	//terminate the section
     	driver.quit();
	}

}
