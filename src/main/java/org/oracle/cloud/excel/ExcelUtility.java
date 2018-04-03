package org.oracle.cloud.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.oracle.cloud.common.DataProvider;

public class ExcelUtility
{

	private static XSSFWorkbook excelWorkBook;
	private static XSSFSheet    excelWSheet;
	private static int MAX_ROW_COUNT=1000;
	private static int MAX_COLUMN_COUNT=50;
	private static int testDataColumn=1;
	
	
	/*
	 * Get Cell Data
	 * @param  Sheet Name
	 * @param  row
	 * @param  column
	 */
	
	public static synchronized String getCellData(XSSFSheet sheet,int row, int column)
	{
		String retValue="";
		
		try
		{
			   XSSFCell cell=sheet.getRow(row).getCell(column);
			   retValue=cell.getStringCellValue();					
		} catch (Exception e)
		{
			// TODO: handle exception			
		}
		
		return retValue;		
	}
	
	public static int getRowNumber(XSSFSheet sheet,String testCaseName)
	{
		String cellData;
		for (int row = 0; row <= MAX_ROW_COUNT; row++)
		{
			cellData=getCellData(sheet,row,0);
			if (cellData.equalsIgnoreCase(testCaseName)) 
			{
				return row;
			}
			
		}
		
		return -1;		
	}
	
	public static DataProvider load(XSSFSheet mySheet, int row)
	{
		DataProvider dataProvider=new DataProvider();
		for (int column =testDataColumn; column < MAX_COLUMN_COUNT; column++) 
		{
			String groupName=getCellData(mySheet,row,column);
			String columnName=getCellData(mySheet,row+1,column);			
			String data=getCellData(mySheet,row+2,column);	
			if ((!groupName.isEmpty())&&(!columnName.isEmpty()))
			{
				dataProvider.set(groupName, columnName, data);
			}		
		}
		return dataProvider;
	}
	/*
	 * Get sheet name from Excel
	 * @param path   Excel path
	 * @param sheet  Sheet name
	 */
	
	public static synchronized XSSFSheet getSheetName(String path,String sheet)
	{
		try 
		{
			FileInputStream fileInputString=new FileInputStream(path);
			excelWorkBook=new XSSFWorkbook(fileInputString);
			excelWSheet=excelWorkBook.getSheet(sheet);
			
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelWSheet;
	}
}
