package org.oracle.cloud.common;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;

import com.google.common.base.Function;
import com.oracle.cloud.exceptions.ActionNotSupportedException;
import com.oracle.cloud.exceptions.ElementNotFoundException;
import com.thoughtworks.selenium.webdriven.commands.KeyEvent;



public class WrappedWebElements implements WebElement
{

	private WrappedWebDriver driver;
	private String name;
	private By findBy;
	
	
	public WrappedWebElements(WrappedWebDriver driver, String name, By findBy)
	{
		super();
		this.driver = driver;
		this.name = name;
		this.findBy = findBy;
	}

	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException
	{
		return null;
	}

	public void clear()
	{
		driver.findElement(findBy).clear();
		Report.log(Status.DONE, elementInfo(name)+" is Set to BLANK", driver);
	}

	public void click() 
	{
		// TODO Auto-generated method stub
		WebElement element=driver.findElement(findBy);
		String staticTimeout=Config.getEnvDetails("browser", "staticTimeOut");
		try 
		{
			
			if (name.startsWith("link")||name.startsWith("button")) 
			{
			   int timeOut;
				if (staticTimeout==null||staticTimeout.equals("")) 
				{
					timeOut=100;
				}
				else
				{
					timeOut=Integer.parseInt(staticTimeout);
				}
				Thread.sleep(timeOut);
			}			
			if (element.getSize()!=null) 
			{
				if (element.isDisplayed()) 
				{
					element.click();
					Report.log(Status.INFO, elementInfo(name)+" is Clicked", driver);
				}
			}
			
		} 
		catch (Exception e)
		{
			throw new ElementNotFoundException(name+" Does not founds");
		}
	}

	public WebElement findElement(By by)
	{
		// TODO Auto-generated method stub
		return driver.findElement(findBy).findElement(by);
	}

	public List<WebElement> findElements(By by)
	{
		// TODO Auto-generated method stub
		return driver.findElement(findBy).findElements(by);
	}

	public String getAttribute(String arg0)
	{
		return driver.findElement(findBy).getAttribute(arg0);
	}

	public String getCssValue(String arg0) 
	{
		return driver.findElement(findBy).getCssValue(arg0);
	}

	public Point getLocation() 
	{
		return driver.findElement(findBy).getLocation();
	}

	public Rectangle getRect() 
	{
		return driver.findElement(findBy).getRect();
	}

	public Dimension getSize()
	{
		// TODO Auto-generated method stub
		return driver.findElement(findBy).getSize();
	}

	public String getTagName() 
	{
		// TODO Auto-generated method stub
		return driver.findElement(findBy).getTagName();
	}

	public String getText() 
	{
		WebElement element=driver.findElement(findBy);
		try
		{
			element.isDisplayed();
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			element=driver.findElement(findBy);
		}
		
		return driver.findElement(findBy).getText();
	}

	public boolean isDisplayed()
	{
		// TODO Auto-generated method stub
		return driver.findElement(findBy).isDisplayed();
	}

	public boolean isEnabled() 
	{
		// TODO Auto-generated method stub
		return driver.findElement(findBy).isEnabled();
	}

	public boolean isSelected() 
	{
		// TODO Auto-generated method stub
		return driver.findElement(findBy).isSelected();
	}
     /**
      * <b>Description</b>Send keys to Text Box 
      * @param  keys text to enter.
      */
	public void sendKeys(CharSequence... keys) 
	{
		WebElement element;
		//return if text are null
		if (keys[0]==null||keys[0].equals("")) 
		{
			return;
		}
		//if keys are Blank 
		if (keys[0].toString().toUpperCase().equalsIgnoreCase("BLANK")) 
		{
			clear();
			return;
		}
		//WebElement element=driver.findElement(findBy);		
		try 
		{
			String time=Config.getEnvDetails("browser", "staticTimeOut");
			int waitTime;
			if (time==null)
			{
				waitTime=200;
			}
			waitTime=Integer.parseInt(time);
			Thread.sleep(waitTime);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		try 
		{
			driver.findElement(findBy).isDisplayed();
		
			element=driver.findElement(findBy);
			element.sendKeys(keys);
		}
		catch (Exception e)
		{
			element=driver.findElement(findBy);
		}
		if (element.getAttribute("type").equalsIgnoreCase("password")) 
		{
            Report.log(Status.INFO, elementInfo(name)+"  Entered", driver);
		} 
		else
		{
             Report.log(Status.INFO, elementInfo(name)+"  entered as "+keys[0].toString(), driver);
		}
		
	}
	public void select(Status selectBy,String dropDownValues)
	{
		WebElement element=driver.findElement(findBy);
		switch (selectBy) 
		{
		case VisiableText:
			new Select(element).selectByVisibleText(dropDownValues);
			break;
			
        case Index:
        	try
        	{
        		if (dropDownValues==null&&dropDownValues.equals(""))
        		{
					throw new ActionNotSupportedException("not right index");
				}
        		int index=Integer.parseInt(dropDownValues);
        		if (index>=0) 
        		{
        			new Select(element).selectByIndex(index);
				}
			}
        	catch (Exception e) 
        	{
				// TODO: handle exception
			}
        	
			break;	
			
        case Value:
			         new Select(element).selectByValue(dropDownValues);
			break;		

		default:
			break;
		}
	}
	public void submit() 
	{
		driver.findElement(findBy).submit();
		
	}

	private static String elementInfo(String name)
	{
		if(name.startsWith("button")&&name.substring(0, 6).equalsIgnoreCase("button"))
		{
			name=name.substring(6, name.length())+" button";
		}
		if(name.startsWith("link")&&name.substring(0, 4).equalsIgnoreCase("link"))
		{
			name=name.substring(4, name.length())+" link";
		}
		if (name.startsWith("textBox")&&name.substring(0, 7).equalsIgnoreCase("textBox"))
		{
			name=name.substring(7,name.length());
		}
		if (name.startsWith("table")&&name.substring(0, 5).equalsIgnoreCase("table"))
		{
			name=name.substring(5,name.length())+" table";
		}
		if (name.startsWith("radioButton")&&name.substring(0, 5).equalsIgnoreCase("radioButton"))
		{
			name=name.substring(11,name.length())+" radioButton";
		}
		if (name.startsWith("checkBox")&&name.substring(0, 5).equalsIgnoreCase("radioButton"))
		{
			name=name.substring(8,name.length())+" checkBox";
		}
		return name;
	}
	public void ajaxWait(WaitType waitType)
	{
		try 
		{
			String ajaxWait=Config.getEnvDetails("browser", "ajaxTimeout");
			int milliWait=Integer.parseInt(ajaxWait);
			WebDriverWait wait=new WebDriverWait(driver, 15);
            switch (waitType)
            {
			case APPEAR:
				//wait.until(ExpectedConditions.);
				break;

            case SELECTED:
				wait.until(ExpectedConditions.elementToBeSelected(findBy));
				break;	
				
            case VISIBLE:
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
				//Report.log(Status.INFO, ""+);
				break;	
								
            case ENABLE:
				wait.until(ExpectedConditions.elementToBeClickable(findBy));
				break;	
				
            case REFRESH:
				wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
				break;	
				
            case CLICKABLE:
				wait.until(ExpectedConditions.elementToBeClickable(findBy));
			//	wait.until(ExpectedConditions.)
            	break;
            	
            case PRESENCE:
            	wait.until(ExpectedConditions.presenceOfElementLocated(findBy)); 
            	break;
            	
            case STALE:
            	 //      WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(findBy)); 
            	    //   wait.until(ExpectedConditions.stalenessOf(element));
				break;
            case SLEEP:
            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			default:
				
				break;
			}
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			Report.log(Status.FAIL, "Failed while doing explicit wait: "+e);
		}
				
		
		
	}
	
	public void uploadFile(String path)
	{
		try 
		{
			if (path.equals(null)||path.equals(""))
			{
				throw new ActionNotSupportedException("File is Null: "+path);
			}
			StringSelection filePath=new StringSelection(path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
			
			Robot robot=new Robot();
			robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyPress(java.awt.event.KeyEvent.VK_V);
			robot.keyRelease(java.awt.event.KeyEvent.VK_V);
			robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
			
			Thread.sleep(3000);
			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			Report.log(Status.INFO, "File: "+path+" Uploaded Sucessfully", driver);
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			throw new ActionNotSupportedException("Something went wrong while uploading file: "+path);
		}
	}
	
	public Long getWindowHeight()
	{
		return (Long)((JavascriptExecutor)driver).executeScript("return window.innerHeight;");
	}
	
	public Long getWindowWidth()
	{
		return (Long)((JavascriptExecutor)driver).executeScript("return window.innerWidth;");
	}
	
	public WebElement getWebElement(){
		return driver.findElement(findBy);
	}
	
	
	public void scrollDownWindow(long width,long height)
	{
		((JavascriptExecutor)driver).executeScript("window.scrollBy("+width+","+height+");");
	}
	public void scrollUpWindow(long width,long height)
	{
		((JavascriptExecutor)driver).executeScript("window.scrollBy("+width+","+height+");");
	}
	
	public void scrollToElement()
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(findBy));
		scrollUpWindow(0,-190);
	}
	
	public void fluentWait()
	{
		try
		{
			FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);			
			wait.withTimeout(1, TimeUnit.MINUTES);
			wait.pollingEvery(200, TimeUnit.MILLISECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(StaleElementReferenceException.class);
			
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver)
				{
				return	driver.findElement(findBy);					
				}
			});
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			
		} 
		
		
		
		
		
		
		
	}
	
	
}
