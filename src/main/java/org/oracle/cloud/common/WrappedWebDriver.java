package org.oracle.cloud.common;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.internal.Killable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WrappedWebDriver implements WebDriver, JavascriptExecutor, FindsById, FindsByClassName, FindsByLinkText, FindsByName, FindsByCssSelector, FindsByTagName, FindsByXPath, HasInputDevices, HasCapabilities, TakesScreenshot, Killable
{
	WebDriver driver=null;
	String browser=null;
	public WrappedWebDriver()
	{
	    browser=Config.getEnvDetails("browser", "browser");
		System.out.println(browser);
		try 
		{
			if (browser.equalsIgnoreCase("IE")) 
			{
                  DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
//                desiredCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
//                desiredCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
                  desiredCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//                desiredCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//                desiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);                
                  desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				  System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\"+"drivers\\IEDriverServer.exe");
                  driver=new InternetExplorerDriver(desiredCapabilities);
			}
			else if (browser.equalsIgnoreCase("Chrome"))
			{
				System.out.println("Setting chrome property");
				System.out.println(System.getProperty("user.dir")+"\\"+"drivers\\chromedriver.exe");
				
			//	DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer(); 
			//	ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

				
	    		ChromeOptions chromeOptions=new ChromeOptions();
	    		chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

				//chromeOptions.addArguments("--disable-extensions");
				DesiredCapabilities caps = new DesiredCapabilities().chrome();
				caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\"+"drivers\\chromedriver.exe");
			    driver=new ChromeDriver(chromeOptions);
			    
			}
			else if (browser.equalsIgnoreCase("firefox")) 
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\"+"drivers\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
		} 
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
	
	public void close() 
	{
		driver.close();
	}

	public WebElement findElement(By arg0)
	{
		return driver.findElement(arg0);
	}

	public List<WebElement> findElements(By arg0) 
	{
		return driver.findElements(arg0);
	}

	public void get(String arg0) 
	{
		// TODO Auto-generated method stub
		driver.get(arg0);
	}

	public String getCurrentUrl() 
	{
		// TODO Auto-generated method stub
		return driver.getCurrentUrl();
	}

	public String getPageSource() 
	{
		// TODO Auto-generated method stub
		return driver.getPageSource();
	}

	public String getTitle() 
	{
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	public String getWindowHandle() 
	{
		// TODO Auto-generated method stub
		return driver.getWindowHandle();
	}

	public Set<String> getWindowHandles()
	{
		// TODO Auto-generated method stub
		return driver.getWindowHandles();
	}

	public Options manage() 
	{
		// TODO Auto-generated method stub
		return driver.manage();
	}

	public Navigation navigate() 
	{
		// TODO Auto-generated method stub
		return driver.navigate();
	}

	public void quit() 
	{
		// TODO Auto-generated method stub
		driver.quit();
	}

	public TargetLocator switchTo() 
	{
		// TODO Auto-generated method stub
		return driver.switchTo();
	}

	public void kill() {
		// TODO Auto-generated method stub
		Process process;
		if (browser.equalsIgnoreCase("Chrome")) 
		{
			 try {
				process=Runtime.getRuntime().exec("chromedriver.exe");
				process.destroy();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			try 
			{
				process=Runtime.getRuntime().exec("chromedriver.exe");
				process.destroy();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			((FirefoxDriver)driver).kill();
		}
	}

	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException 
	{
		TakesScreenshot tkScreenShot=(TakesScreenshot) getAugmentedDriver(driver);
		
		return tkScreenShot.getScreenshotAs(arg0);
	}

	public WebDriver getAugmentedDriver(WebDriver driver)
	{
		if (driver.getClass().equals(RemoteWebDriver.class))
		{
			Augmenter augment=new Augmenter();
			driver=augment.augment(driver);
		}
		else
		{
			driver=this.driver;
		}
		return driver;
	}
	
	public Capabilities getCapabilities() {
		// TODO Auto-generated method stub
		HasCapabilities capabilities=null;
		capabilities=((HasCapabilities)getAugmentedDriver(driver));
		return capabilities.getCapabilities();
	}

	public Keyboard getKeyboard() {
		// TODO Auto-generated method stub
		
		return ((HasInputDevices)driver).getKeyboard();
	}

	public Mouse getMouse() {
		// TODO Auto-generated method stub
		return ((HasInputDevices)driver).getMouse();
	}

	public WebElement findElementByXPath(String arg0) {
		// TODO Auto-generated method stub
		return ((RemoteWebDriver)driver).findElementByXPath(arg0);
	}

	public List<WebElement> findElementsByXPath(String arg0) {
		// TODO Auto-generated method stub
		return ((RemoteWebDriver)driver).findElementsByXPath(arg0);
	}

	public WebElement findElementByTagName(String arg0) {
		// TODO Auto-generated method stub
		return ((RemoteWebDriver)driver).findElementByTagName(arg0);
	}

	public List<WebElement> findElementsByTagName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement findElementByCssSelector(String arg0) {
		// TODO Auto-generated method stub
		return  ((RemoteWebDriver)driver).findElementByCssSelector(arg0);
	}

	public List<WebElement> findElementsByCssSelector(String arg0) {
		// TODO Auto-generated method stub
		return  ((RemoteWebDriver)driver).findElementsByCssSelector(arg0);
	}

	public WebElement findElementByName(String arg0) {
		// TODO Auto-generated method stub
		return  ((RemoteWebDriver)driver).findElementByName(arg0);
	}

	public List<WebElement> findElementsByName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement findElementByLinkText(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement findElementByPartialLinkText(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElementsByLinkText(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElementsByPartialLinkText(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement findElementByClassName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElementsByClassName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement findElementById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElementsById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object executeAsyncScript(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object executeScript(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return ((JavascriptExecutor)driver).executeScript(arg0, arg1);
	}

}
