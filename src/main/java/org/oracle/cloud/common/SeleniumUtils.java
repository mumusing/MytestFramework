package org.oracle.cloud.common;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;

import com.oracle.cloud.exceptions.ActionNotSupportedException;
import com.oracle.cloud.exceptions.ElementNotFoundException;

public class SeleniumUtils
{
	 private WrappedWebDriver driver; 
	public SeleniumUtils(WrappedWebDriver driver)
	{
		this.driver=driver;
	}
	
	public void acceptAlert(Alerts key)
	{
		try
		{
		Alert alert=driver.switchTo().alert();
		switch (key) 
		{
		case Accept:
			        String message= alert.getText();
			        alert.accept();    
			        Report.log(Status.INFO, "Alert Accepted Sucessfully", driver);
			break;
		case Dismiss:
			        alert.dismiss();
			        Report.log(Status.INFO, "Alert Dismissed Sucessfully", driver);
			break;

		default:
			        alert.accept();
			        Report.log(Status.INFO, "Alert Dismissed Sucessfully", driver);
			break;
		}		
	}
	catch(Exception e)
	{
		throw new ElementNotFoundException("Below Exception occure while Accepting Alert: "+e);
	}
	}
	
	public void switchWindow(String title) throws Exception
	{
		Set<String> windowHandles=driver.getWindowHandles();
		String currentHandle=driver.getWindowHandle();
		boolean isSwitch=false;
		String currentTitle="";
		try 
		{
			for (String windowHandle : windowHandles)
			{
				 currentTitle=driver.switchTo().window(windowHandle).getTitle();
				 System.out.println(currentTitle);
				if (currentTitle.equalsIgnoreCase(title)||currentTitle.contains(title)) 
				{
					driver.switchTo().window(windowHandle);
					isSwitch=true;
					Report.log(Status.INFO, "Switched sucessfully To window: "+currentTitle, driver);
					break;
				}				
			}
			if (!isSwitch) 
			{
				throw new ActionNotSupportedException("Did not find window with Title: "+currentTitle);
			}		
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			throw e;
		}		
	}
	public void switchToFrame(String nameOrId,CommonEnums by) throws Exception
	{
		boolean isSwitch=false;
		try
		{
		switch (by)
		{
		case ById:
			       driver.switchTo().frame(nameOrId);
			       Report.log(Status.INFO, "Sucessfully switch To frame with ID: "+nameOrId, driver);
			       isSwitch=true;
			break;
			
        case ByName:
			       driver.switchTo().frame(nameOrId);
			       Report.log(Status.INFO, "Sucessfully switch To frame with NAME: "+nameOrId, driver);
			       isSwitch=true;
			break;	

		default:
			       driver.switchTo().defaultContent();
			       Report.log(Status.INFO, "Sucessfully switch To Default Frame:  "+nameOrId, driver);
			       isSwitch=true;
			break;
		}
		if (!isSwitch)
		{
			throw new ActionNotSupportedException("Does Not Find Frame with Name or Id: "+nameOrId);
		}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			throw e;
		}
		
	}
	
	public void switchFrame(int index)
	{
		try
		{
			driver.switchTo().frame(index);
			Report.log(Status.INFO, "Sucessfully switch to frame with index: "+index, driver);
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			throw new ActionNotSupportedException("Does not find Frame with Index: "+index);
		}		
	}
	
	public void mouseHoverAndClick(WebElement mainElement,WebElement subElement) throws Exception
	{
		try 
		{
			if (mainElement.equals("")) 
			{
				throw new ActionNotSupportedException("Element are Null: ");
			}
			Actions actions=new Actions(driver);
			actions.moveToElement(mainElement).perform();
			Report.log(Status.INFO, "Moved to main Element: "+mainElement, driver);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			actions.moveToElement(subElement).click().perform();
			Report.log(Status.INFO, "Moved to sub Element and Click: "+subElement, driver);			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			throw e;
		}	
	}
	
	public void dragAndDrop(WebElement fromElement,WebElement toElement)
	{
		try
		{
			//One way to Drag And Drop 
			Actions actions=new Actions(driver);
			actions.clickAndHold(fromElement).perform();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			actions.moveToElement(toElement).perform();
			Report.log(Status.INFO, "Move To Element: "+fromElement.getText(), driver);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			actions.release(toElement).perform();
			Report.log(Status.INFO, "Drop To Element: "+toElement.getText(), driver);
			
			//Second way to Drag And Drop
		//	actions.dragAndDrop(fromElement, toElement).build().perform();
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			Report.log(Status.INFO, "Something went wrong while draging element:  "+fromElement.getText()+" To Eelement: "+toElement.getText(), driver);
		}
		
	}
	public void sliderActions(WebElement element,int xOffset, int yOffset)
	{
		try
		{
			Actions actions=new Actions(driver);
			actions.dragAndDropBy(element, xOffset, yOffset);
			Report.log(Status.INFO, "Moved Element from: "+xOffset+" :To"+yOffset, driver);
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			Report.log(Status.INFO, "Something went wrong while sliding element:  "+element, driver);
		}
		
		
	}
	
	public WebElement waitForElement(By locator,int timeOutInSeconds)
	{
		WebElement element=null;
		try
		{
			WebDriverWait explictWait=new WebDriverWait(driver, timeOutInSeconds);
		element=explictWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		Report.log(Status.Pass, "Element: "+element.getText()+" located on page");
		
		
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			Report.log(Status.Fail, "Element : "+element.getText()+" located on page because of exception: "+ e);
		}
		return element;
		
		
	}
	
	
	
	
}
