package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomatingLoacalhostTest {
	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		String project_name="Qspider123";
		
		// auto genrate method stub
		WebDriver driver = WebDriverManager.chromedriver().create();
		 
		
		//maximize the browser window
		driver.manage().window().maximize();
		
		//implicitlywait for 10sec
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//explicitlywait for 10sec
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//enter the url of the application
		driver.get("http://localhost:8084/");
		
		//enter the username in the username text field
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		
		//enter the password in the password text field
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		
		//click on sign in button
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		
		//click on projects button
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		
		//click on create project link
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		
		//enter the project name in prorect name text field
		driver.findElement(By.name("projectName")).sendKeys("Qspider123");
		
		
		//driver.findElement(By.name("teamSize")).sendKeys("0");
		
		//Enter the project manager name in the project manager text field
		driver.findElement(By.name("createdBy")).sendKeys("Rohan");
		
		//find the dropdown path
		WebElement dropDown = driver.findElement(By.xpath("//label[text()='Project Status ']/..//select[@name='status']"));
		
		//create select object
		Select select=new Select(dropDown);
		
		//select the created option in the dropdown
		select.selectByVisibleText("Created");
		
		//click on add project button
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		try {
			
			//get register for my sql DB
			Driver driverRef= new Driver();
			DriverManager.registerDriver(driverRef);
			
			//connect to mysql DB using URL
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			
			//create a statement
			Statement statement = connection.createStatement();
			
			//write a query
			String query = "select * from project";
			
			//execute a query
			ResultSet result = statement.executeQuery(query);
			boolean flag = false;
			//fetch all the column data
			while (result.next()) {
				String allProjects = result.getString(3);
				//verify the data in DB
				if (allProjects.contains(project_name)) {
					flag=true;
					break;
				}				
			}
			
			//verify project is created or not
			if (flag) {
				System.out.println("Project is created");
			}
			else {
				System.out.println("Project is not created ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//close my sql coonection
			connection.close();
			//terminate the section
			driver.quit();
		}
	}

}
