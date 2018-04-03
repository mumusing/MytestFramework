package org.oracle.cloud.common;

import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class Assert 
{

	public static void assertTrue(ValidationType vType,boolean condition,String message)throws AssertionError
	{
		try
		{	
			org.testng.Assert.assertTrue(condition, message);
            report(vType, message, null);			
		} 
		catch (AssertionError e)
		{
			// TODO: handle exception
            report(vType, message, null);
		}		
	}
	public static void verifyTrue(ValidationType vType,boolean condition,String message)
	{
		SoftAssert softAssert=new SoftAssert();
		
		try
		{
			softAssert.assertTrue(condition, message);
			System.out.println("Compared home title");
            report(vType, message, null);			
 
		} 
		catch (AssertionError e)
		{
			// TODO: handle exception
            report(vType, message, e);

		}
		softAssert.assertAll();
	}
	public static void assertFalse(ValidationType vType,boolean condition,String message)throws AssertionError
	{
		try
		{	
			org.testng.Assert.assertFalse(condition, message);
            report(vType, message, null);			
		} 
		catch (AssertionError e)
		{
			// TODO: handle exception
            report(vType, message, e);
		}		
	}
	
	public static void verifyFalse(ValidationType vType,boolean condition,String message)
	{
		SoftAssert softAssert=new SoftAssert();
		
		try
		{
			softAssert.assertFalse(condition, message);
            report(vType, message, null);			
 
		} 
		catch (AssertionError e)
		{
			// TODO: handle exception
            report(vType, message, e);

		}
		softAssert.assertAll();
	}
	
	public static void assertNotNull(ValidationType vType,Object object,String message)throws AssertionError
	{
		try
		{
			org.testng.Assert.assertNotNull(object, message);
            report(vType, message, null);			
		}
		catch (AssertionError e)
		{
			// TODO: handle exception
            report(vType, message, e);
		}		
	}
	public static void assertNull(ValidationType vType,Object object,String message)throws AssertionError
	{
		try
		{
			org.testng.Assert.assertNull(object, message);
            report(vType, message, null);			
		}
		catch (AssertionError e)
		{
			// TODO: handle exception
            report(vType, message, e);
		}		
	}
	
	
	public static void report(ValidationType vType,String message,AssertionError exp)
	{
		if (exp==null)
		{
		 switch (vType)
		 {
		case ASSERT:
        case Assert:
			        Report.log(Status.PASS, message);
			break;
			
        case VERIFY:
	
        case Verify:
	                Report.log(Status.Pass, message);
			break;
			
        case NONE:
			
			break;
			
		default:
			break;
		} 
			
			
		} 
		else
		{
			switch (vType)
			 {
			case ASSERT:
	        case Assert:
				        Report.log(Status.FAIL, message);
				        throw exp;				
				
	        case VERIFY:
		
	        case Verify:
		                Report.log(Status.Fail, message);
				break;
				
	        case NONE:
				
				break;
				
			default:
				break;
			} 
		}
		
	}
}
