package com.crm.Vtiger.Contacts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsUsingNameAnilTest {

	public static void main(String[] args) throws IOException {
		    // TODO Auto-generated method stub

		    WebDriver driver=null;
		   
		    //fetch the data from property file
		    FileInputStream fileinputStream=new FileInputStream("./src/test/resources/CommunData.properties");
		    
		    Properties properties=new Properties();
		    properties.load(fileinputStream);
		    
		    String URL = properties.getProperty("url");
		    String USERNAME = properties.getProperty("username");
		    String PASSWORD = properties.getProperty("password");
		    String BROWSER = properties.getProperty("browser");
		    
		    //Execute the chrome browser
		    if (BROWSER.equalsIgnoreCase("chrome")) {
			   driver=WebDriverManager.chromedriver().create();
			}
		    
		    //Execute the firefox browser
		    else if (BROWSER.equalsIgnoreCase("firefox")) {
				driver=WebDriverManager.firefoxdriver().create();
			}
		    
		    //Execute the MS edge browser
		    else if (BROWSER.equalsIgnoreCase("edge")) {
				driver=WebDriverManager.edgedriver().create();
			}
		    
		    //Execute opera browser
		    else  {
				driver=WebDriverManager.safaridriver().create();
			}
		    
		    
			//maximize the window
			driver.manage().window().maximize();
			
			//enter url of the application
		    driver.get(URL);
			
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
			
			//click on contacts link
			driver.findElement(By.xpath("//td[8]//a[.='Contacts']")).click();
			
			//click on create contacts link
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			
			//Find path of dropdown
			WebElement dropDown1 = driver.findElement(By.name("salutationtype"));
			
			//create select class
			Select select2=new Select(dropDown1);
			
			//select mr. option in dropdown
			select2.selectByValue("Mr.");
			
			//enter first name in first name text field
			driver.findElement(By.name("firstname")).sendKeys("Anil");

			//Enter last name in last name text field
			driver.findElement(By.name("lastname")).sendKeys("Desai");
			
			//click on select organization button
			driver.findElement(By.xpath("//tr[5]//td[2]//img[@src='themes/softed/images/select.gif']")).click();

			//get the parent window id
			String parentWindow2 = driver.getWindowHandle();
			
			//get all windows Id's
			Set<String> allWindowIds2 = driver.getWindowHandles();
			
			for (String windowid2 : allWindowIds2) {
				//get the each window titles and switch driver control into sub window
				 String windowTitle = driver.switchTo().window(windowid2).getTitle();
				 System.out.println("windowTitle ="+windowTitle);
				
				 //compare window title
				 if (windowTitle.equals("")) {

				    //enter organization name in search text field	
					driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("TestYantra software solution pvt ltd457");

					//click on search button 
					driver.findElement(By.name("search")).click();
					
					//click on TestYantra software solution pvt ltd457 link
					driver.findElement(By.xpath("//a[.='TestYantra software solution pvt ltd457']")).click();
					
					//driver.switchTo().alert().accept();
					
					//close the loop
					break;
				}		
			}
			
			//driver control swithback to parent window
			driver.switchTo().window(parentWindow2);
			
			//click on save button
			driver.findElement(By.name("button")).click();
			

			//Expected organization name in organization created page
			String expected="Anil";
					
			//Actual organization name in organization created page
			String actual = driver.findElement(By.id("dtlview_First Name")).getText();

			System.out.println(actual);
			//Verifing Organization created or not
			if (actual.contains(expected)) {
			System.out.println("Organization page is verified and Organization is created ");
						
					}
			else {
			System.out.println("Organization page is verified and Organization is not created ");
					}
					
			//click on administrater
			driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
					
			//click on SignOut link
			driver.findElement(By.xpath("//a[.='Sign Out']")).click();
			     	
			//terminate the section
			driver.quit();	

	}

}
