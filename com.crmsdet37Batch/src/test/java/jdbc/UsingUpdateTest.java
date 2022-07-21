package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;

import com.mysql.cj.jdbc.Driver;

public class UsingUpdateTest {

	public static void main(String[] args) throws SQLException {


		//get register for my sql DB
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);

		//connect to mysql DB using URL
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");

		//create a statement
		Statement statement = connection.createStatement();

		//write a query
		String query = "insert into project values('TY_PROJ_003','GIRISH','10/07/2022','QSPIDER','created','101')";

		//execute a query
		int update = statement.executeUpdate(query);
		if (update==1) {
			System.out.println("data is created");
		} 
		else {
			System.out.println("data is not created");
		}

		connection.close();
	}

}
