package org.oracle.cloud.common;

import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

public abstract class TestCase 
{

protected	WrappedWebDriver driver=null;
protected	ExtentTest logger=null;
protected   SeleniumUtils seleniumUtils=null;
public   String testCaseName=null;	
public DataProvider dp=null;	
	public void initialize(String testName)
	{
		try
		{
			this.testCaseName=testName;
			startTest(testName);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
			//driver.get("https://www.facebook.com/");
		}		
	}
	
	/**
	 * <b>Description</b>Start test
	 * @throws Exception 
	 * 
	 */
	public void startTest(String testName) throws Exception
	{
		System.out.println("starting Test"+testName);
		driver=getDriver();
		driver.manage().window().maximize();
		seleniumUtils=new SeleniumUtils(driver);
		Report.startExtentReport(testName, driver);
		this. dp=DataProvider.readTestData(testCaseName);
		String env=Config.getEnvDetails("aut", "env");
		driver.get(Config.getEnvDetails(env, "URL"));
	}
	/**
	 * <b>Description</b>End Test Cases
	 */
	public void end(String testName)
	{
		
		System.out.println("Executing end "+testName);
		try 
		{
			driver.close();
			driver.quit();
		} catch (Exception e) 
		{
			// TODO: handle exception
			Report.log(Status.FAIL, "Exception Occured while ending the test case due to :"+e, driver);
		}	
		finally
		{
			Report.endExtentReport();
			driver=null;
		}
	}
	public WrappedWebDriver getDriver()
	{
		if (driver==null)
		{
			driver=new WrappedWebDriver();
		}
		return driver;
	}
}
