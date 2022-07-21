package others;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PolymarpismTest {
	public static void main(String[] args) throws IOException {
		
		WebDriver driver=null;
		
		//Fetch the data from properties file(External file)
		FileInputStream fileinputstream1=new FileInputStream(".\\src\\test\\resources\\CommunData.properties");
		Properties property=new Properties();
		property.load(fileinputstream1);		
		String URL = property.getProperty("url");
		String USERNAME = property.getProperty("username");
		String PASSWORD = property.getProperty("password");
		String BROWSER = property.getProperty("browser");
				
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
	}

}
