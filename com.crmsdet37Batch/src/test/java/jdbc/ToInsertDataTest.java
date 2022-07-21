package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ToInsertDataTest {

	public static void main(String[] args) throws SQLException {

		//get register for my sql DB
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);

		//connect to mysql DB using URL
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");

		//create a statement
		Statement statement = connection.createStatement();

		//write a query
		String query = "insert into student values('ravi','kishan','528','1587/02/15','male','rajaram')";
		
		//execute a query
		int number = statement.executeUpdate(query);
		if (number==1) {
			System.out.println("Data is created in table");
		}
		else {
			System.out.println("Data is not created in table");
			
		}
		System.out.println();
		
		//write a query
		String query1 = "select * from student";
		
		//execute a query
		ResultSet result = statement.executeQuery(query1);
		while (result.next()) {
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4)+"\t"+result.getString(5)+"\t"+result.getString(6));
		}
		
		//close database connection
		connection.close();
	}

}
