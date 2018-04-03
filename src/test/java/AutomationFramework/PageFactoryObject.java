package AutomationFramework;

import org.oracle.cloud.common.TestCase;

import oracle.opera.cloud.admin.pages.AdminHomePage;
import oracle.opera.cloud.admin.pages.AdminNavigationPage;
import oracle.opera.cloud.admin.pages.OperaControlsPage;
import oracle.opera.cloud.pages.LoginPage;

public class PageFactoryObject extends TestCase
{
	
   public  AdminHomePage homePage=new AdminHomePage(driver);
   public AdminNavigationPage navigationPage=new AdminNavigationPage(driver);
   public OperaControlsPage operaControlPage=new OperaControlsPage(driver);
   public LoginPage loginPage=new LoginPage(driver);
	
	
}
