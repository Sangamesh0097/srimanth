package excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertMultipleDataIntoExcelTest {

	public static void main(String[] args) throws IOException {
		    // TODO Auto-generated method stub
		

		    //fetch data from excel sheet
 		    FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Excel.xlsx");
		    
 		    Workbook workbook = WorkbookFactory.create(fis);
		    
 		    Sheet sheet = workbook.getSheet("Sheet2");
		
			WebDriver driver=null;
			
			//Fetch the data from properties file(External file)
			FileInputStream fileInputStream =new FileInputStream(".\\src\\test\\resources\\CommunData.properties");
			
			Properties property=new Properties();
			
			property.load(fileInputStream);
			
			String URL = property.getProperty("url");
			
			String USERNAME = property.getProperty("username");
			
			String PASSWORD = property.getProperty("password");
			
			String BROWSER = property.getProperty("browser");
			
			//print the browser name
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
		    
			//find all input tags
		    List<WebElement> allLinks = driver.findElements(By.xpath("//input"));
		    int count = allLinks.size();
		    
		    for (int i = 0; i < count; i++) {
		
			    Row row = sheet.createRow(i);
			    Cell cell = row.createCell(2);
			    cell.setCellValue(allLinks.get(i).getAttribute("name"));
		    }
		
		
		    //insert data into excel
		    FileOutputStream fileOutputStream=new FileOutputStream("./src/test/resources/Excel.xlsx");
		    workbook.write(fileOutputStream);

	}

}
