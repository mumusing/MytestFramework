package AutomationFramework;

import org.testng.annotations.Test;

import com.oracle.cloud.utils.DateHelper;

import oracle.opera.cloud.admin.pages.AdminHomePage;
import oracle.opera.cloud.admin.pages.AdminNavigationPage;
import oracle.opera.cloud.admin.pages.OperaControlsPage;
import oracle.opera.cloud.admin.pages.TransactionGroupsPage;
import oracle.opera.cloud.pages.HomePage;
import oracle.opera.cloud.pages.LoginPage;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import org.apache.http.util.Asserts;
import org.oracle.cloud.common.Assert;
import org.oracle.cloud.common.TestCase;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;
import org.testng.annotations.AfterMethod;

public class AcceptanceTestCases extends TestCase
{
	@BeforeMethod
	public void beforeMethod(Method method)
	{		
	    initialize(method.getName());
	}
   @Test
   public void loginTestCase() throws Exception 
   {
	   try
	   {
	  //Login into Application
	   LoginPage loginPage=new LoginPage(driver);
	   loginPage.loginIntoApplication(dp);
	   
	   //Move to administration Page
	   HomePage homePage=new HomePage(driver);
	   homePage.verifyTitle(ValidationType.ASSERT);
	   homePage.navigateToAdminHomePage();
	   
	   //Admin Home Page
	   AdminHomePage adminHomePage=new AdminHomePage(driver);
	   adminHomePage.verifyTitle(ValidationType.ASSERT);
	   
	   AdminNavigationPage adminNavPage=new AdminNavigationPage(driver);
	   adminNavPage.naviagateToOperaControlsPage();
	   
	   //Activate License
	   OperaCloudUtils operaCloudUtils=new OperaCloudUtils(driver);
	   OperaControlsPage operaControlsPage=new OperaControlsPage(driver);
	   operaControlsPage.verifyTitle(ValidationType.ASSERT);
	   operaCloudUtils.selectGroups(operaControlsPage.getElement("listGroup"), "Accounts Receivables", "Accounts Receivables");
	   }
	   catch(Exception e)
	   {
		   Report.log(Status.FAIL, "Failed because of exception: "+e);
		   throw e;
	   }
   }
  
   @Test
   public void createTransactionGroup() throws Exception
   {
	   try
	   {
		   String tranCode=dp.get("TransactionGroups", "txtCode")+DateHelper.GetRandomNumber(3);
		   dp.set("TransactionGroups", "txtCode", tranCode);
		   
		 //Login into Application
		   LoginPage loginPage=new LoginPage(driver);
		   loginPage.loginIntoApplication(dp);
		   
		   //Move to administration Page
		   HomePage homePage=new HomePage(driver);
		   homePage.verifyTitle(ValidationType.ASSERT);
		   homePage.navigateToAdminHomePage();
		   
		   //Admin Home Page
		   AdminHomePage adminHomePage=new AdminHomePage(driver);
		   adminHomePage.verifyTitle(ValidationType.ASSERT);
		   
		   //Navigate to Transaction Group Page
		   AdminNavigationPage adminNavPage=new AdminNavigationPage(driver);
		   adminNavPage.navigateToTransactionGroupPage();
		   
		   //Transaction Group Page
		   TransactionGroupsPage transcationGroupPage=new TransactionGroupsPage(driver);
		   transcationGroupPage.verifyTitle(ValidationType.ASSERT);
		   transcationGroupPage.clickTemplate();
		   transcationGroupPage.verifyTitle(ValidationType.ASSERT);
		   
		   transcationGroupPage.clickTemplateNew();
		   transcationGroupPage.enterTransactionGroupInfo(dp);
		   transcationGroupPage.clickSaveButton();
		   transcationGroupPage.verifyTitle(ValidationType.ASSERT);
		 //  Thread.sleep(5000);
		   //Click Property tab
		   transcationGroupPage.clickPropertyTab();
		   transcationGroupPage.clickCopyButton();
		   transcationGroupPage.configureParameters(dp);
		   transcationGroupPage.clickCopyAndContinue();
		   transcationGroupPage.clickBackToTransactionGroups();
		   
           //Search Transaction Group
		   transcationGroupPage.searchTransactionGroup(dp);
		   transcationGroupPage.clickSearchButton();
		   
		   //Search Transaction Group
		   OperaCloudUtils operaUtils=new OperaCloudUtils(driver);
		   String transactionGroup=operaUtils.getTableDataFromTable(transcationGroupPage.getElement("tableTransactionGroup"), 0, 1);
		   System.out.println(dp.get("TransactionGroups", "txtCode")+"  :  "+transactionGroup);
		   Assert.assertTrue(ValidationType.ASSERT, transactionGroup.equalsIgnoreCase(dp.get("TransactionGroups", "txtCode")), "Comparing Transaction Group");
		   
		   		   
	   } 
	 catch (Exception e) 
	   {
		// TODO: handle exception
		 Report.log(Status.FAIL, "Failed because of exception: "+e);
		 throw e;
		 
	   }
	   
	   
	   
   }
   
   
   
   
  @AfterMethod
  public void afterMethod(Method method) 
  {
	  end(method.getName());
  }

}
