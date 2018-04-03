package com.oracle.cloud.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.oracle.cloud.common.Config;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;

public class DataBaseHelper 
{
	private String DB_SERVER;
	private String DB_PORT;
	private String DB_HOST;
	private String DB_SCHEMA;
	private String DB_USER;
	private String DB_PWD;
	Connection connection=null;
	Statement  statement=null;
	String driver="oracle.jdbc.driver.OracleDriver";
	//  String url="jdbc:oracle:thin:@//"+DB_HOST+":"+DB_PORT+"/"+DB_SERVER;
  public DataBaseHelper()
  {
	  String env=Config.getEnvDetails("aut", "env");
	  DB_SERVER=Config.getEnvDetails(env, "DB_SERVER");
	  DB_PORT=  Config.getEnvDetails(env, "DB_PORT");
	  DB_HOST=  Config.getEnvDetails(env, "DB_HOST");
	  DB_SCHEMA=  Config.getEnvDetails(env, "DB_SCHEMA");
	  DB_USER=  Config.getEnvDetails(env, "DB_USER");
	  DB_PWD=  Config.getEnvDetails(env, "DB_PWD");
  }


  public ResultSet executeSelectQuery(String query) 
  {
	  ResultSet resultSet=null;
	  try
	  {	
	    String url="jdbc:oracle:thin:@//"+DB_HOST+":"+DB_PORT+"/"+DB_SERVER;
		//Load Driver
		Class.forName(driver);
		//Get Connection
		connection=getConnection(url, DB_USER, DB_PWD);
		//Create Statement
		statement=createStatement();
		//Result Set
		resultSet=statement.executeQuery(query); 
		Report.log(Status.INFO, "Select Query Executed Sucessfully: "+query);
      }
      catch(Exception e)
	  {
			Report.log(Status.FAIL, "Action failed because Of: "+e);
			
	  }
	  return resultSet;
  }
	public boolean executeActionQuery(String query)
	{
		boolean isSucessfully=false;
		try	
		{
		    String url="jdbc:oracle:thin:@//"+DB_HOST+":"+DB_PORT+"/"+DB_SERVER;
			//Load Driver
			Class.forName(driver);
			//Get Connection
			connection=getConnection(url, DB_USER, DB_PWD);
			//Create Statement
			statement=createStatement();
			//Result Set
			int status=statement.executeUpdate(query);
			isSucessfully=true;
			Report.log(Status.INFO, "execute Action Query Executed Sucessfully: "+query);
		} catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
			Report.log(Status.FAIL, "Action failed because Of: "+e);
			isSucessfully=false;		
		}
		return isSucessfully;
	}
	public void loadDriver(String driver) 
	{
		
			try 
			{
				Class.forName(driver);
				Report.log(Status.INFO, "Driver loaded sucessfully:--- "+driver);
			} catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				Report.log(Status.INFO, "Failed while loading Driver because of exception: "+e);
				e.printStackTrace();
			}
		//	Report.log(Status.INFO, "Sucessfully Loaded driver: "+driver, driver);
		
	}
	public Connection getConnection(String url, String user, String password)
	{
		if (connection==null)
		{
			 try {
				connection=DriverManager.getConnection(url, user, password);
				Report.log(Status.INFO, "Connection Establish sucessfully to DataBase: ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Report.log(Status.INFO, "Failed while connecting to dataBase because of exception: "+e);
				e.printStackTrace();
			}
	    }
	   
		return connection;
	}
	public Statement createStatement()
	{
		if (statement==null) 
		{
			try
			{
				statement=connection.createStatement();
				Report.log(Status.INFO, "Statement created sucessfully--- ");
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				Report.log(Status.INFO, "Failed while creating statement with dataBase because of exception: "+e);
			}
		}
		return statement;
	}
}



