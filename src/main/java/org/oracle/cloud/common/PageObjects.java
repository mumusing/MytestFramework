package org.oracle.cloud.common;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;

import com.oracle.cloud.exceptions.ActionNotSupportedException;

public abstract class PageObjects 
{
    protected WrappedWebDriver driver;
    protected LinkedHashMap<String, By>elements=new LinkedHashMap<String, By>();
	
	
	public PageObjects(WrappedWebDriver driver)
	{
		this.driver=driver;
	}
	/**
	 * <b>Description</b>Add elements in Map.
	 * @param name
	 * @param property
	 */
	public void addElement(String name,By property)
	{
		elements.put(name, property);
	}
	/**
	 * <b>Description</b>Return element from collection if exists
	 * @param name
	 * @return web element
	 */
	public WrappedWebElements getElement(String name)
	{
		if(!elements.containsKey(name)) 
		{
			Report.log(Status.FAIL, "Element:--- "+name+" --- does not exist"+this.getClass().getSimpleName(), driver);
		    throw new ActionNotSupportedException("Element not Exists: "+name+this.getClass().getSimpleName());
		}
		return new WrappedWebElements(driver,name,elements.get(name));
	}
	
	public By getElementBy(String name)
	{
		if(!elements.containsKey(name)) 
		{
			Report.log(Status.FAIL, "Element:--- "+name+" --- does not exist"+this.getClass().getSimpleName(), driver);
		    throw new ActionNotSupportedException("Element not Exists: "+name+this.getClass().getSimpleName());
		}
		return elements.get(name);
	}
	
	abstract public void verifyTitle(ValidationType vType);
	
	public void verifyMessage(ValidationType validationType,String elementObject,String message) 
	{
		try 
		{
			System.out.println("executing verifyMessage method");
			getElement(elementObject).ajaxWait(WaitType.VISIBLE);
			//getElement(elementObject).fluentWait();			
			String text=getElement(elementObject).getText();
			Assert.verifyTrue(validationType, text.trim().equals(message), "Verify: "+message+" is displayed");
			System.out.println("Assert  executed sucessfully: ");
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
            Report.log(Status.FAIL, "Failed while verifying title: "+message);
		}
		
		
		
	}
	
	
}
