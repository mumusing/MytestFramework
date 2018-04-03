package org.oracle.cloud.reports;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.oracle.cloud.common.TestCase;
import org.oracle.cloud.common.WrappedWebDriver;
import org.apache.commons.logging.*;
import com.oracle.cloud.utils.DateHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report
{
   public static String resultsPath=getResultPath();
   public static String File_Name="/ExtentReports.html";
   public static ExtentReports extentReport;
   public static ExtentTest looger;
   public static String testName="";
   public static String screenShotPath;
   public static int screenShotCounter;
   private static WrappedWebDriver driver;
	public static synchronized String getResultPath()
	{
		//System.out.println(method.getName());
		resultsPath=System.getProperty("user.dir")+"/report/"+ "Run"+DateHelper.getCurrentDateTime("yyyyMMdd")+DateHelper.getCurrentDateTime("HHmmss");
		if (!new File(resultsPath).isDirectory()) 
		{
			new File(resultsPath).mkdir();
		}
		return resultsPath;
	}
	
	public static synchronized void startExtentReport(String testCase,WrappedWebDriver webDriver)
	{		testName=testCase;
	        driver=webDriver;
			extentReport=new ExtentReports(resultsPath+File_Name, false);
			System.out.println(resultsPath+File_Name);
			extentReport.addSystemInfo("HostName","Local").addSystemInfo("Env", "Cloud");
			looger=extentReport.startTest(testName);
		//testName=testCaseName;
	//	extentReport.
	//	extentTest=extentReport.startTest(testCaseName);
	}
	public static synchronized void endExtentReport()
	{
		extentReport.endTest(looger);
		extentReport.flush();
	}
	public synchronized static void endExtentTest()
	{
		extentReport.close();		
	}
	public synchronized static void log(Status status,String message,WebDriver driver)
	{
		switch (status) 
		{
		case PASS:
			screenShotPath=resultsPath+"/ScreenShots/"+testName+"/"+"Screen_"+screenShotCounter+".png";
        	takeScreenShot(screenShotPath,driver);
	        looger.log(LogStatus.PASS, message+looger.addScreenCapture(screenShotPath));                    
	        screenShotCounter++;
			break;
			
        case FAIL:
        	screenShotPath=resultsPath+"/ScreenShots/"+testName+"/"+"Screen_"+screenShotCounter+".png";
        	takeScreenShot(screenShotPath,driver);
	        looger.log(LogStatus.FAIL, message+looger.addScreenCapture(System.getProperty("user.dir")+"/"+screenShotPath));                    
	        screenShotCounter++;
	        break;	
			
        case INFO:
                     looger.log(LogStatus.INFO, message);

			break; 	

		default:
			break;
		}	
	}
	
	public synchronized static void log(Status status,String message)
	{
		switch (status) 
		{
		case PASS:
			screenShotPath=resultsPath+"/ScreenShots/"+testName+"/"+"Screen_"+screenShotCounter+".png";
        	takeScreenShot(screenShotPath,driver);
	        looger.log(LogStatus.PASS, message+looger.addScreenCapture(screenShotPath));                    
	        screenShotCounter++;
			break;
			
		case Pass:
	        looger.log(LogStatus.PASS, message);                    
			break;	
			
        case FAIL:
        	screenShotPath=resultsPath+"/ScreenShots/"+testName+"/"+"Screen_"+screenShotCounter+".png";
        	takeScreenShot(screenShotPath,driver);
	        looger.log(LogStatus.FAIL, message+looger.addScreenCapture(System.getProperty("user.dir")+"/"+screenShotPath));                    
	        screenShotCounter++;
	        break;	
	        
        case Fail:
	        looger.log(LogStatus.FAIL, message);                    
			break;	   
			
        case INFO:
                     looger.log(LogStatus.INFO, message);

			break; 	

		default:
			break;
		}	
	}
	
	
	
	//public  synchronized static void log
	public static synchronized void takeScreenShot(String path,WebDriver driver)
	{
		try
		{
			File sourceFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(path));
           
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
}
