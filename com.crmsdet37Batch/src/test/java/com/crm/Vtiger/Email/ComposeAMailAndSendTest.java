package com.crm.Vtiger.Email;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ComposeAMailAndSendTest {
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

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

		//click on email
		driver.findElement(By.xpath("//a[.='Email']")).click();

		//click on compose
		driver.findElement(By.xpath("//a[.='Compose']")).click();

		//get all window id's
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindowIds = driver.getWindowHandles();

		//handled diffrent windows
		for (String windowid : allWindowIds) {
			System.out.println("window id="+windowid);
			String windowTitle = driver.switchTo().window(windowid).getTitle();
			System.out.println("windowTitle ="+windowTitle);
			if (windowTitle.equals("Compose Mail")) {
				driver.findElement(By.xpath("//td[2]//input[@name='parent_name']")).sendKeys("sangameshgoudru96@gmail.com");

				//click on vendors dropdown
				WebElement dropDown = driver.findElement(By.name("parent_type"));
				Select select=new Select(dropDown);
				select.selectByValue("Vendors");

				//click on vendors link 
				driver.findElement(By.xpath("//img[@title=\"Select\"]")).click();

				String SubWindow = driver.getWindowHandle();

				//get all window id's
				Set<String> newAllWindow = driver.getWindowHandles();

				//handled diffrent windows
				for (String windowid1 : newAllWindow) {

					WebDriver windows123 = driver.switchTo().window(windowid1);

					String newWindowTitle = windows123.getTitle();

					String windowName="";

					System.out.println("windowTitle ="+newWindowTitle);

					if (newWindowTitle.equals(windowName)) {

						driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("sangamesh0097@gmail.com");

						//click on email dropdown
						WebElement dropDown1 = driver.findElement(By.name("search_field"));

						Select select1=new Select(dropDown1);

						select1.selectByValue("email");

						driver.findElement(By.name("search")).click();

						driver.findElement(By.xpath("//a[.='Pramodh']")).click();

						System.out.println("New window is closed");

						break;
					}
				}

				//driver control switch back to sub window
				driver.switchTo().window(SubWindow);

				//enter email adress
				driver.findElement(By.xpath("//tr//td[2]//input[@name='bccmail']")).sendKeys("sangameshgoudru96@gmail.com");

				driver.findElement(By.xpath("//tr//td[2]//input[@name='subject']")).sendKeys("Laptop Document");

				driver.findElement(By.name("file_0")).sendKeys("C:\\Users\\hi\\OneDrive\\Desktop\\sangu.jpg");

				//click on send link
				driver.findElement(By.name("Send")).click();

				break;

			}
		}

		//driver control switch back to parent window
		driver.switchTo().window(parentWindow);

		System.out.println("Documents is emailed ");
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
		//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();


	}
}
