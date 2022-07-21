package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyingProjectsCreatedOrNotTest {

	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		WebDriver driver=null;
		String projectName = "Apple123";
		try {

			//get register for my sql DB
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			//connect to mysql DB using URL
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");

			//create a statement
			Statement statement = connection.createStatement();

			//write a query
			String query = "insert into project values('TY_PROJ_009','guru','11/07/2022','Apple123','created','10')";

			//execute a query
			int result1 = statement.executeUpdate(query);
			if (result1==1) {
				System.out.println("data is created");
			}
			else {
				System.out.println("data is not created");
			}



			// auto genrate method stub
			driver = WebDriverManager.chromedriver().create();


			//maximize the browser window
			driver.manage().window().maximize();

			//implicitlywait for 10sec
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			//explicitlywait for 10sec
			//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

			//enter the url of the application
			driver.get("http://localhost:8084/");

			//enter the username in username text field
			driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");

			//enter the password in password text field
			driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");

			//click on sign in button
			driver.findElement(By.xpath("//button[.='Sign in']")).click();

			//click on projects button
			driver.findElement(By.xpath("//a[.='Projects']")).click();

			//find the table
			List<WebElement> allOption = driver.findElements(By.xpath("//div[@class='col-sm-6']/../../..//tr//td[2]"));
			boolean temp = false;
			for (WebElement element : allOption) {
				if (element.getText().equals(projectName)) {
					temp=true;
					System.out.println(element.getText());
				}
			} 
			if(temp==true)
			{
				System.out.println("Project is created in Database, PASS");
			}else
			{
				System.out.println("Project is not created in Database, FALSE");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			connection.close();
			driver.quit();
		}

	}

}
