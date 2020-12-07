package application;

import java.sql.*;

/**
 * 
 * Description: This class will establish a connection to a mySQL database.
 * Date: 		November 22, 2020
 * 
 * @author Kamilya
 *
 */
public class DBconnect 
{
	
	
	/**
	 * The database name
	 */
	public static String dbname;
	
	private String user = "root";
	private String pass = "pass";
	
	Connection con;
	
	final String DRIVER = "com.mysql.jdbc.Driver";
	final String URL = "jdbc:mysql://localhost:3306/wordOccurrences"; //?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	/**
	 * Creates the connection to the database
	 * 
	 */
	public DBconnect()
	{
		try 
		{
			//Connect to the database
			con = DriverManager.getConnection(URL, user, pass);
			
			if (con != null) 
			{
				System.out.println("Connected");
			}
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
