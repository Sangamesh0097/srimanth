package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ToFetchDatabaseTest {
	
	public static void main(String[] args) throws SQLException {
		//create driver class
		Driver driver=new Driver();
		
		//register the driver 
		DriverManager.registerDriver(driver);
		
		//add connection to database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
		
		//create statement
		Statement statement = connection.createStatement();
		
		//write a query to fetch data
		String query = "select * from student";
		
		//execute query
		ResultSet result = statement.executeQuery(query);
		
		//print all data 
		while (result.next()) {
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4)+"\t"+result.getString(5)+"\t"+result.getString(6));
		}
		
		//close database connection
		connection.close();
	}

}
