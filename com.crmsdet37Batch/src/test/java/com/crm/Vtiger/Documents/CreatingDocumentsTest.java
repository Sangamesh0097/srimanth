package com.crm.Vtiger.Documents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatingDocumentsTest {
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
		
		//click on document		
		driver.findElement(By.xpath("//td[14]//a[.='Documents']")).click();
		
		//click on create document
	    driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		
	    
		//Fetch the data from the excel Sheet
		FileInputStream fileInputStream2 = new FileInputStream("./src/test/resources/Excel.xlsx");
				
		//Fetch First name from Excel
		Row row = WorkbookFactory.create(fileInputStream2).getSheet("Vtiger").getRow(1);
		String title = row.getCell(19).toString();
		
	    //enter title of discription
		driver.findElement(By.name("notes_title")).sendKeys(title);
			
		
		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
		
		//entering data into frame
		frame.sendKeys("Document is created");
		
		//transfer the driver control to frame
		driver.switchTo().frame(frame);
		
		//Mouse using perpose
		Actions actions=new Actions(driver);
		actions.moveByOffset(500, 600).contextClick().perform();

		// driver.switchTo().defaultContent();
    	driver.findElement(By.name("filename")).sendKeys("C:\\Users\\hi\\Downloads\\123.docx");
		  
		//click on save button
    	driver.findElement(By.name("button")).click();
	    
    	System.out.println("Document is created and File is also added to the document ");
		
    	//Click on adminstrater
    	driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
				
    	//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		//terminate the section
		driver.quit();		 
			 
}
}
