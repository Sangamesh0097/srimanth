package com.crm.Vtiger.Assets;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAsstes {

	public static void main(String[] args) throws IOException {
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

		//mouse overing on more option
		actions.moveToElement(driver.findElement(By.xpath("//a[.='More']"))).perform();

		//click on Assets
		driver.findElement(By.name("Assets")).click();
		
		//click on create Assets
        driver.findElement(By.xpath("//img[@title='Create Asset...']")).click();
		
        //fetch data from excel file
        FileInputStream fileInputStreamXL=new FileInputStream("./src/test/resources/Excel.xlsx");
        Workbook workbook = WorkbookFactory.create(fileInputStreamXL);
        Sheet sheet = workbook.getSheet("Vtiger");
        String assetName = sheet.getRow(1).getCell(22).toString();
        String serialNumber = sheet.getRow(2).getCell(23).toString();
        String productName = sheet.getRow(1).getCell(4).toString();
        String subject = sheet.getRow(2).getCell(12).toString();
        String customerName = sheet.getRow(2).getCell(2).toString();
        
        //enter serial number
        driver.findElement(By.xpath("//tr[4]//td[2]//input[@name='serialnumber']")).sendKeys(serialNumber);
        
        //enter sold date
        WebElement soldDate = driver.findElement(By.name("datesold"));  
        soldDate.clear();
        soldDate.sendKeys("2022-07-25"); 
        
        //enter service date
        WebElement serviceDate = driver.findElement(By.name("dateinservice"));
        serviceDate.clear();
        serviceDate.sendKeys("2025-05-20");
        
		//click on create product name link
		driver.findElement(By.xpath("//tr[3]//td[4]//img[@src='themes/softed/images/select.gif']")).click();

		//get parent window id
		String parentWindow = driver.getWindowHandle();
		
		//get all windows ids
		Set<String> allWindowIds = driver.getWindowHandles();
		
		//handled diffrent window
		for (String windowid : allWindowIds) {
			String windowTitle = driver.switchTo().window(windowid).getTitle();
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
		
		//click on create invoice name link
		driver.findElement(By.xpath("//tr[7]//td[2]//img[@src='themes/softed/images/select.gif']")).click();
		
		//get all windows ids
		Set<String> allWindowIds1 = driver.getWindowHandles();
		
		//handled diffrent window
		for (String windowid1 : allWindowIds1) {
			String windowTitle = driver.switchTo().window(windowid1).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {
				
				//select subject option in dropdown
				WebElement searchDropDown = driver.findElement(By.name("search_field"));
				Select select = new Select(searchDropDown);
				select.selectByVisibleText("Subject");
				
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(subject);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='Sending Sales order to Test yantra softw...']")).click();
				break;
			}		
		}
		
		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);
		
		//click on create contack
		driver.findElement(By.xpath("//tr[9]//td[2]//img[@src='themes/softed/images/select.gif']")).click();

		//get all window id's
        Set<String> allWindowIds2 = driver.getWindowHandles();

		//handled diffrent windows
		for (String windowid2 : allWindowIds2) {
			String windowTitle = driver.switchTo().window(windowid2).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("")) {

				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(customerName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='TestYantra software solution pvt ltd457']")).click();
				
				break;
			}		
		}

		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);
		
		//enter asste name
		driver.findElement(By.id("assetname")).sendKeys(assetName);
  
		//click on save button
		driver.findElement(By.name("button")).click();

		//expected result
		String expected=subject;
		
		
		
		//actual result
		String actual = driver.getPageSource();

		//verify Assets created or not
		if (actual.contains(expected)) {
			System.out.println("Assets page is verified and Assets is created");

		}
		else {
			System.out.println("Assets page is verified and Assets is not created");
		}

		//click on administrater
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();

		//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();

	}

}
