package com.crm.Vtiger.Invoice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateInvoice {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver=null;
		
		//fetch data from property file
		FileInputStream fileInputStream=new FileInputStream("./src/test/resources/CommunData.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String URL = properties.getProperty("url");
        String BROWSER = properties.getProperty("browser");
        String USERNAME = properties.getProperty("username");
		String PASSWORD = properties.getProperty("password");

		//Execute chrome browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver=WebDriverManager.chromedriver().create();
		}
		
		//Execute firefox browser
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver=WebDriverManager.firefoxdriver().create();
		}
		
		//Execute MSEdge browser
		else if (BROWSER.equalsIgnoreCase("edge")) {
			driver=WebDriverManager.edgedriver().create();
		}
		
		//Execute chrome browser
		else {
			driver=WebDriverManager.chromedriver().create();
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

		//mouse overing more option
		actions.moveToElement(driver.findElement(By.xpath("//a[.='More']"))).perform();

		//click on invoice
		driver.findElement(By.name("Invoice")).click();
		
		//click on create invoice
        driver.findElement(By.xpath("//img[@title='Create Invoice...']")).click();
		
        //fetch data from excel file
        FileInputStream fileInputStreamXL=new FileInputStream("./src/test/resources/Excel.xlsx");
        Workbook workbook = WorkbookFactory.create(fileInputStreamXL);
        Sheet sheet = workbook.getSheet("Vtiger");
        String subject = sheet.getRow(2).getCell(12).toString();
        String billingAddress = sheet.getRow(1).getCell(15).toString();
        String shippingAddress = sheet.getRow(1).getCell(16).toString();
        String quantity = sheet.getRow(1).getCell(11).toString();
        String amount = sheet.getRow(1).getCell(10).toString();
        String lastName = sheet.getRow(3).getCell(9).toString();
        String fullName = sheet.getRow(1).getCell(21).toString();
        String organizationName = sheet.getRow(2).getCell(2).toString();
        String productName = sheet.getRow(1).getCell(4).toString();
        
        //enter subject name
        driver.findElement(By.name("subject")).sendKeys(subject);
        
		//click on create contack
		driver.findElement(By.xpath("//tr[5]//td[2]//img[@src='themes/softed/images/select.gif']")).click();

		//parent window id
		String parentWindow = driver.getWindowHandle();

		//get all window id's
		Set<String> allWindowIds = driver.getWindowHandles();

		//handled diffrent windows
		for (String windowid : allWindowIds) {
			String windowTitle = driver.switchTo().window(windowid).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {

				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(lastName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='Sanjay kumar']")).click();
				driver.switchTo().alert().accept();

				break;
			}		
		}

		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);
		
		//handle calender
		WebElement date = driver.findElement(By.name("invoicedate"));
		date.clear();
		date.sendKeys("2022-07-25");
		
		//click on create contack
		driver.findElement(By.xpath("//tr[8]//td[2]//img[@src='themes/softed/images/select.gif']")).click();

		//get all window id's
        Set<String> allWindowIds1 = driver.getWindowHandles();

		//handled diffrent windows
		for (String windowid1 : allWindowIds1) {
			String windowTitle = driver.switchTo().window(windowid1).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {

				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(organizationName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='TestYantra software solution pvt ltd457']")).click();
				driver.switchTo().alert().accept();
				break;
			}		
		}

		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);
		
		//enter billing address
		driver.findElement(By.name("bill_street")).sendKeys(billingAddress);
		
		//enter shipping address
		driver.findElement(By.name("ship_street")).sendKeys(shippingAddress);
		
		//click on product link
		driver.findElement(By.id("searchIcon1")).click();
		
		//get all windows ids
		Set<String> allWindowIds3 = driver.getWindowHandles();
		
		//handled diffrent window
		for (String windowid3 : allWindowIds3) {
			String windowTitle = driver.switchTo().window(windowid3).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(productName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='Laptop']")).click();
				break;
			}		
		}
		
		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);
		
		//enter quantity
		driver.findElement(By.id("qty1")).sendKeys(quantity);
		
		WebElement price = driver.findElement(By.id("listPrice1"));
		price.clear();
		
		//enter price
		price.sendKeys(amount);
		
		//click on save button
		driver.findElement(By.name("button")).click();

		//expected result
		String expected=subject;
		
		//actual result
		String actual = driver.getPageSource();

		//verify Invoice created or not
		if (actual.contains(expected)) {
			System.out.println("Invoice page is verified and Invoice is created");

		}
		else {
			System.out.println("Invoice page is verified and Invoice is not created");
		}

		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();

		//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();


	}

}
