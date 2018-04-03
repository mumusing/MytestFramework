package oracle.opera.cloud.pages;

import org.openqa.selenium.By;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WaitType;
import org.oracle.cloud.common.WrappedWebDriver;

public class HomePage extends PageObjects
{

	public HomePage(WrappedWebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
		addElement("spanHomeTitle", By.xpath("//div[contains(@id,'pt1:ode_dsbrd_tmpl_bs:bc1::oc')]//a[text()='Home']"));
		addElement("linkMenu", By.xpath("//a[@id='pt1:oc_pg_pt:pt_pt1:ode_pg_sdbr_itm::disAcr']"));
		addElement("linkAdministration", By.xpath("//a[contains(@id,'pt1:oc_pg_pt:pt_pt1:oc_pg_sdmn:j_idt')]//span[text()='Administration']"));		
	}

	public void navigateToAdminHomePage()
	{
		getElement("linkMenu").click();
		getElement("linkAdministration").ajaxWait(WaitType.VISIBLE);
		getElement("linkAdministration").click();
	}

	@Override
	public void verifyTitle(ValidationType vType) 
	{
		// TODO Auto-generated method stub
		verifyMessage(vType,"spanHomeTitle","Home");
	}
	
	
}
