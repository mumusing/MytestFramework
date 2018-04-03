package oracle.opera.cloud.pages;

import org.openqa.selenium.By;
import org.oracle.cloud.common.Config;
import org.oracle.cloud.common.DataProvider;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.UserInfo;
import org.oracle.cloud.common.UserList;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WrappedWebDriver;


public class LoginPage extends PageObjects
{

	public LoginPage(WrappedWebDriver driver) 
	{
		super(driver);
		
		addElement("textBoxUserName", By.id("username"));
		addElement("textBoxPassword", By.id("password"));
		addElement("buttonLogin", By.cssSelector(".form-button"));		
	}

	public void loginIntoApplication(DataProvider dp)
	{
		
		UserInfo user=UserList.getUser(Config.getEnvDetails("aut", "env"));
		
		getElement("textBoxUserName").sendKeys(user.getUserName());
		getElement("textBoxPassword").sendKeys(user.getPassWord());
		getElement("buttonLogin").click();
	}

	

	@Override
	public void verifyTitle(ValidationType vType) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
