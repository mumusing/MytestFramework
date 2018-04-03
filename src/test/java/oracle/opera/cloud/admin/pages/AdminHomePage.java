package oracle.opera.cloud.admin.pages;

import org.openqa.selenium.By;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WrappedWebDriver;

public class AdminHomePage extends PageObjects
{

	public AdminHomePage(WrappedWebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
		addElement("spanAdminHomeTitle", By.xpath("//h1[text()='OPERA Cloud Administration']"));
	//	addElement("linkMenu", By.xpath("//img[contains(@id,'pt_pt1:ode_pg_sdbr_itm::icon')]"));
	//	addElement("linkAdministration", By.xpath("//a[contains(@id,'pt1:oc_pg_pt:pt_pt1:oc_pg_sdmn:j_idt9')]"));
        
		//Navigate to Transaction Group
		addElement("linkFinancial", By.xpath("//a[text()='Financial']"));
		addElement("spanAdminHomeTitle", By.xpath("//h1[text()='OPERA Cloud Administration']"));
		addElement("spanAdminHomeTitle", By.xpath("//h1[text()='OPERA Cloud Administration']"));

		//
	}

	public void navigateToAdministration()
	{
		getElement("linkMenu").click();
		getElement("linkAdministration").click();
	}

	public void navigateToTransactionGroupPage()
	{
		
		
		
	}

	@Override
	public void verifyTitle(ValidationType vType)
	{
		// TODO Auto-generated method stub
		verifyMessage(vType, "spanAdminHomeTitle", "OPERA Cloud Administration");
	}
	
	
}
