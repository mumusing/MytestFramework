package org.oracle.cloud.common;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.oracle.cloud.excel.ExcelUtility;

public class DataProvider 
{
protected    HashMap<String, HashMap<String, String>> dataProvider=new HashMap<String, HashMap<String,String>>();
	public void setData()
	{
		
	}
	public static DataProvider readTestData(String testCaseName) throws Exception
	{
		XSSFSheet dataSheet=ExcelUtility.getSheetName("DataSheet.xlsx", "testData");
		int rowNumber=ExcelUtility.getRowNumber(dataSheet, testCaseName);
		
		if (rowNumber==-1)
		{
			throw new Exception("Test case with name: "+testCaseName+" does not exists");
		}
		//int groupName=ExcelUtility.getCellData(dataSheet, rowNumber, column);
		DataProvider dp=ExcelUtility.load(dataSheet, rowNumber);
		return dp;
	}

	
	public void set(String group,String column,String data)
	{
		if (!dataProvider.containsKey(group))
		{
			dataProvider.put(group, new HashMap<String, String>());
		}
		dataProvider.get(group).put(column, data);
	}
	
	public String get(String group,String column)
	{
		if (dataProvider.containsKey(group)&&dataProvider.get(group).containsKey(column)) 
		{
			return dataProvider.get(group).get(column);
		}
		else
		{
			return "";
		}
	}
}
	
	
	
		
		
		
		
		

