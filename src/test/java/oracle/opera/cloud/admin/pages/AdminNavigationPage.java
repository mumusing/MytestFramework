package oracle.opera.cloud.admin.pages;

import org.openqa.selenium.By;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.SeleniumUtils;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WrappedWebDriver;

import AutomationFramework.OperaCloudUtils;

public class AdminNavigationPage extends PageObjects
{
    
	public AdminNavigationPage(WrappedWebDriver driver) 
	{
		super(driver);

		
		//Link Transaction Sub Group
		addElement("linkFinancial", By.linkText("Financial"));
		addElement("linkTransactionManagement", By.xpath("//tr[contains(@id,'pt1:oc_pg_pt:pt_pt1:dm1:j_idt')]//td[contains(text(),'Transaction Management')]"));
	//	addElement("linkTransactionManagement", By.xpath("//tr[contains(@id,'pt1:oc_pg_pt:pt_pt1:dm1:j_idt')]//td[contains(text(),'Transaction Management')]"));
		addElement("linkTransactionSubGroup", By.xpath("//tr[contains(@id,'pt1:oc_pg_pt:pt_pt1:dm1:j_idt')]//td[contains(text(),'Transaction Subgroups')]"));	
		addElement("linkTransactionGroup", By.xpath("//td[text()='Transaction Groups']//parent::tr//parent::tbody"));	

	
	     //Navigate to Opera Control Page
		addElement("linkEnterprise", By.linkText("Enterprise"));
		addElement("linkOPERAControls", By.xpath("//td[text()='OPERA Controls']"));

	
	
	
	
	
	}

	public void naviagateToOperaControlsPage()
	{
		getElement("linkEnterprise").click();
		getElement("linkOPERAControls").click();
	}
	
	public void navigateToTransactionSubGroup() throws Exception
	{
		SeleniumUtils seleniumUtils=new SeleniumUtils(driver);
		getElement("linkFinancial").click();
		getElement("linkTransactionManagement").click();
		seleniumUtils.mouseHoverAndClick(getElement("linkTransactionManagement"), getElement("linkTransactionSubGroup"));		
	}
	public void navigateToTransactionGroupPage() throws Exception
	{
		OperaCloudUtils operaUtils=new OperaCloudUtils(driver);
		getElement("linkFinancial").click();
		getElement("linkTransactionManagement").click();
		operaUtils.selectJump(getElement("linkTransactionGroup"), "Transaction Groups");
		//seleniumUtils.mouseHoverAndClick(getElement("linkTransactionManagement"), getElement("linkTransactionGroup"));		
	}
	

	@Override
	public void verifyTitle(ValidationType vType) {
		// TODO Auto-generated method stub
		
	}
	
}
