package oracle.opera.cloud.pages;

import org.openqa.selenium.By;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.SeleniumUtils;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WrappedWebDriver;

public class NavigationPage extends PageObjects
{
    
	public NavigationPage(WrappedWebDriver driver) 
	{
		super(driver);

		
		//Link Transaction Sub Group
		addElement("linkFinancial", By.linkText("Financial"));
		addElement("linkTransactionManagement", By.xpath("//tr[contains(@id,'pt1:oc_pg_pt:pt_pt1:dm1:j_idt')]//td[contains(text(),'Transaction Management')]"));
	//	addElement("linkTransactionManagement", By.xpath("//tr[contains(@id,'pt1:oc_pg_pt:pt_pt1:dm1:j_idt')]//td[contains(text(),'Transaction Management')]"));
		addElement("linkTransactionSubGroup", By.xpath("//tr[contains(@id,'pt1:oc_pg_pt:pt_pt1:dm1:j_idt')]//td[contains(text(),'Transaction Subgroups')]"));	
	}

	public void navigateToTransactionSubGroup() throws Exception
	{
		SeleniumUtils seleniumUtils=new SeleniumUtils(driver);
		getElement("linkFinancial").click();
		getElement("linkTransactionManagement").click();
		seleniumUtils.mouseHoverAndClick(getElement("linkTransactionManagement"), getElement("linkTransactionSubGroup"));		
	}

	

	@Override
	public void verifyTitle(ValidationType vType) {
		// TODO Auto-generated method stub
		
	}
	
}
