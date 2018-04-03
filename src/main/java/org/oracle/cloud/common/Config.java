package org.oracle.cloud.common;

import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.oracle.cloud.excel.ExcelUtility;

public class Config 
{
	 public static volatile String env=null;
	 private static volatile LinkedHashMap<String, LinkedHashMap<String, String>> envDetails;
   public static synchronized void init()
   {
		envDetails = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	  //Read Common Details file
	  XSSFSheet sheetName =ExcelUtility.getSheetName("Config.xlsx", "CommanDetails");
	   
	  for (int row = 1; row < 100; row++) 
	  {
		String app_val=ExcelUtility.getCellData(sheetName, row, 0).trim();
		if (!envDetails.containsKey(app_val))
		{
			envDetails.put(app_val,new LinkedHashMap<String, String>());
		}  
		String var_val=ExcelUtility.getCellData(sheetName, row, 1);
		envDetails.get(app_val).put(var_val, ExcelUtility.getCellData(sheetName, row, 2));		
	  }
	  
	  //Read Env Details sheet file
	  sheetName =ExcelUtility.getSheetName("Config.xlsx", "EnvDetails");
	  if (env==null) 
	  {
		  env=getEnvDetails("aut","env");
	  }
	  for (int row = 1; row < 100; row++)
	  {
		  String env_val=ExcelUtility.getCellData(sheetName, row, 0);
		  if (env_val.equalsIgnoreCase(env))
		  {
			  if (!envDetails.containsKey(env)) 
				{
						envDetails.put(env, new LinkedHashMap<String, String>());	
				}
				 String var_val=ExcelUtility.getCellData(sheetName, row, 1);
				 String VAL=ExcelUtility.getCellData(sheetName, row, 2);
				 envDetails.get(env).put(var_val, ExcelUtility.getCellData(sheetName, row, 2));
				 } 
				  
		   }
	//Read UserDetails Excel
	  sheetName =ExcelUtility.getSheetName("Config.xlsx", "UserDetails");	  
	  for (int row = 1; row < 10; row++) 
	  {
		String env_details=ExcelUtility.getCellData(sheetName, row, 0);
		
		if (env_details=="")
		{
			break;
		}
		UserInfo userInfo=new UserInfo();
		String userName=ExcelUtility.getCellData(sheetName, row, 1);
		String userPass=ExcelUtility.getCellData(sheetName, row, 2);
		
		userInfo.setUserName(userName);
		userInfo.setPassWord(userPass);
		
		UserList userList=new UserList();
		userList.putUser(env_details, userInfo);		
	  }	  
	  System.out.println("Print HashMap: "+envDetails);
   }
   
   public static String getEnvDetails(String app,String var)
   {
	   if (envDetails==null)
	   {
		   init();
	   }
	   if (envDetails.containsKey(app)&&envDetails.get(app).containsKey(var)) 
	   {
		 return envDetails.get(app).get(var).toString();
	   }
	   else
	   {
		   return "";
	   }
   }  
}
