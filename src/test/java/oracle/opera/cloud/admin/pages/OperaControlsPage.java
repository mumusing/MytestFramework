package oracle.opera.cloud.admin.pages;

import org.openqa.selenium.By;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WrappedWebDriver;

public class OperaControlsPage extends PageObjects
{

	public OperaControlsPage(WrappedWebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
		addElement("spanOPERAControls", By.xpath("//h1[text()='OPERA Controls']"));
		
		addElement("listGroup", By.xpath("//div[contains(@id,'pt1:oc_pg_pt:pt_pt1:r1:1:pt1:oc_excpt_tmpl:pglGrp')]"));
	//	addElement("listGroup", By.xpath("//div[contains(@id,'pt1:oc_pg_pt:pt_pt1:r1:1:pt1:oc_excpt_tmpl:pglGrp')]"));
		
	}

	@Override
	public void verifyTitle(ValidationType vType) 
	{
		// TODO Auto-generated method stub
		verifyMessage(vType, "spanOPERAControls", "OPERA Controls");
	}

}
