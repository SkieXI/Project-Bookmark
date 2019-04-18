package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class DBConn
{
	public static Connection conn() throws SQLException
	{
	Connection conn = null;
	String url = "jdbc:derby:C:\\Users\\Joe\\MyDB";
	String username = "root";
	String password = "derby";
	try 
	
	{
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connected to DB");
		return conn;
	}
	catch (SQLException e) 
	{
		System.out.println("Error connecting to " + url +".");
		e.printStackTrace();
	} finally 
		{
		if (conn != null) 
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	return null;
}
}
