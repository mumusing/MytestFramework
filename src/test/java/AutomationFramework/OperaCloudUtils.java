package AutomationFramework;

import java.util.Iterator;
import java.util.List;

import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.oracle.cloud.common.Config;
import org.oracle.cloud.common.TestCase;
import org.oracle.cloud.common.WaitType;
import org.oracle.cloud.common.WrappedWebElements;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;

public class OperaCloudUtils 
{
	WebDriver driver;
	
	public OperaCloudUtils(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
    public void selectGroups(WrappedWebElements element,String groupName,String controlName)
    {      
    	   boolean isOperationPerformed=false;
    	   String errorMessage="";
    	   Config.getEnvDetails("", "");
           List<WebElement> groupList=element.findElements(By.tagName("div"));
    	   for (WebElement webElement : groupList) 
    	   {
    		   String xPath="//a[contains(@id,'cilGrp')]//span[text()='"+groupName+"']";
    		       		   
    		   try
    		   {
    			   WebElement findElement=webElement.findElement(By.xpath(xPath));  
    			   boolean bool= findElement.isDisplayed();
    			   if (!bool)
    			   {
    				   ((WrappedWebElements)findElement).ajaxWait(WaitType.VISIBLE);
				   }
    			   if (bool)
    			   {
    				   findElement.click();
    				   Report.log(Status.INFO, "Group: "+ groupName+" Selected Sucessfully");
				   }
    			   else
    			   {
    				   errorMessage="Something went wrong while selecting group name";
    			   }
    			   
			   } 
    		   catch (Exception e)
    		   {
				   Report.log(Status.FAIL, "Something went wrong while selecting group "+ groupName+" because of exception +"+e);    			   
			   }   
    		   try 
    		   {
        		  WebElement findField =driver.findElement(By.xpath("//input[@id='pt1:oc_pg_pt:pt_pt1:r1:1:pt1:oc_excpt_tmpl:s2:it2::content']"));
        		  boolean bool= findField.isDisplayed();
        		  if (!bool)
        		  {
        			  ((WrappedWebElements)findField).ajaxWait(WaitType.ENABLE);
				  }
        		  if (bool)
        		  {
        			  findField.sendKeys(controlName);
        			  driver.findElement(By.id("pt1:oc_pg_pt:pt_pt1:r1:1:pt1:oc_excpt_tmpl:s2:cbFind")).click();
        			  Report.log(Status.PASS, "Clicked Search Button sucessfully");
        			  WebElement inactiveButton=driver.findElement(By.id("//button[contains(@id,'pt1:oc_excpt_tmpl:gts1:iParent:0:dc2:cbActivateFunc')]"));
        			  ((WrappedWebElements)inactiveButton).ajaxWait(WaitType.VISIBLE);
        			  inactiveButton.click();
        			  WebElement activeButton=driver.findElement(By.id("//button[contains(@id,'pt1:oc_excpt_tmpl:gts1:iParent:0:dc2:cbActivateFunc')]"));
        			  activeButton.click();
        		      driver.findElement(By.linkText("Back to Home")).click();
        		      isOperationPerformed=true;
        		  }
        		  else
        		  {
        			  errorMessage="Something went wrong while activating button";
        		  }
        		  if (isOperationPerformed) 
        		  {
					 break;
				  }
			   } 
    		   catch (Exception e) 
    		   {
				// TODO: handle exception
    			   Report.log(Status.PASS, errorMessage+" : "+e);
			   }
    		   
    		   
    		   
    		   
    		   
    		   
		   }
    	
    	
    }
	public void selectJump(WrappedWebElements element,String jumpName)
	{
		boolean isFound=false;
		List<WebElement>rows=element.findElements(By.tagName("tr"));
		try 
		{
			for (WebElement row : rows) 
			{
				List<WebElement> columns=row.findElements(By.tagName("td"));
				for (WebElement column : columns)
				{
					System.out.println(column.getText().equalsIgnoreCase(jumpName));
					System.out.println(column.getText());
					if (column.getText().equalsIgnoreCase(jumpName))
					{
						
						column.click();
						isFound=true;
						Report.log(Status.INFO, "Clicked on Jump: "+jumpName);
						break;
					}
				}
				if (isFound) 
				{
					break;
				}
			}
			if (!isFound) 
			{
				Report.log(Status.INFO, "Did Not found Jump: "+jumpName);
			}
		} 
			catch (Exception e) 
		{
				
			// TODO: handle exception
				Report.log(Status.FAIL, "Sowething went erong while selecting Jump: "+jumpName+" due to exception: "+e);
				
				
		}		
	}
	public String getTableDataFromTable(WebElement webElement,int row,int column)
	{
		String value="";
		try
		{
		int i=0,j=0;
		boolean isFound=false;
		List<WebElement>rows=webElement.findElements(By.tagName("tr"));
		for (WebElement webElement2 : rows)
		{
			if (i==row) 
			{
				List<WebElement>columns=webElement2.findElements(By.tagName("td"));
				for (WebElement webElement3 : columns) 
				{
					if (j==column)
					{
						value=webElement3.getText();
						Report.log(Status.INFO, "Get value of Row:"+row+" Column: "+column);
						isFound=true;
					}
					j++;
				}
				i++;
			}			
		}
		if (!isFound)
		{
			Report.log(Status.FAIL, "Did not find row: "+row+" column :"+column);
		}
				
	}
		catch(Exception e)
		{
			Report.log(Status.FAIL, "Failed because of Exception: "+e);
		}
		return value;
	}
	
}
