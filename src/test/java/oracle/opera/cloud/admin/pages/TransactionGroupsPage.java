package oracle.opera.cloud.admin.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.oracle.cloud.common.DataProvider;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.SeleniumUtils;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WaitType;
import org.oracle.cloud.common.WrappedWebDriver;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;

public class TransactionGroupsPage extends PageObjects
{

	public TransactionGroupsPage(WrappedWebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
		addElement("spanTransactionGroups", By.xpath("//h1[text()='Transaction Groups']"));
        
		
		addElement("linkTemplate", By.xpath("//div[contains(@id,'pt1:oc_scrn_tmpl:template::ti')]"));
		addElement("linkTemplateNew", By.xpath("//div[@id='pt1:oc_pg_pt:pt_pt1:r1:1:pt1:oc_scrn_tmpl:template']//span[text()='New']"));
		addElement("textBoxCode", By.xpath("//input[contains(@id,'oc_pnl_cmp:oc_pnl_tmpl:pt_pt1:fe2:ic1:occ_ic_it:odec_it_it::content')]"));
		addElement("textBoxDescription", By.xpath("//input[contains(@id,'pt1:oc_pnl_cmp:oc_pnl_tmpl:pt_pt1:fe3:it4:odec_it_it::content')]"));
		addElement("linkSeq", By.xpath("//a[contains(@id,'pt1:oc_pnl_cmp:oc_pnl_tmpl:pt_pt1:fe4:sn1:occ_sn_in:odec_in_it:inc')]"));
		addElement("buttonTemplateSave", By.xpath("//div[contains(@id,'pt1:oc_pnl_cmp:oc_pnl_tmpl:pt_pt1:oc_pnl_axnbr:odec_axn_br_i:1:odec_axn_br_axn_btn')]"));		
	
	
	    //Transaction Groups Copy
	//	addElement("linkCopy", By.xpath("//a[contains(@id,'oc_pnl_tmpl:oc_pnl_tlbr_axns:odec_axn_lnks_i:1:odec_axn_lnks_axn')]"));
		addElement("textBoxTransactionGroup", By.xpath("//input[contains(@id,'pt1:oc_pnl_cmp:oc_pnl_tmpl:pt_pt1:fe2:lov2:odec_lov_itLovetext::content')]"));
		addElement("textBoxTargetProperties", By.xpath("//input[contains(@id,'pt_pt1:fe3:plov2:dc_lov1:odec_lov_itLovetext::content')]"));		
		addElement("buttonSave", By.xpath("//div[contains(@id,'pt1:oc_pnl_cmp:oc_pnl_tmpl:pt_pt1:oc_pnl_axnbr:odec_axn_br_i:0:odec_axn_br_axn_btn')]"));
		addElement("buttonCopyAndContinue", By.xpath("//div[contains(@id,'oc_pnl_tmpl:pt_pt1:oc_pnl_axnbr:odec_axn_br_i:0:odec_axn_br_axn_btn')]"));
		addElement("linkProperty", By.xpath("//div[contains(@id,'pt1:oc_scrn_tmpl:property::ti')]//div[contains(@id,'pt1:oc_scrn_tmpl:property::ti::_afrTabCnt')]"));
		addElement("linkCopy", By.xpath("//a[contains(@id,'oc_pnl_tmpl:oc_pnl_tlbr_axns:odec_axn_lnks_i:1:odec_axn_lnks_axn')]"));
		addElement("linkBackToTransactionGroups", By.xpath("//a[contains(@id,'oc_pnl_tmpl:oc_pnl_tlbr_axns:odec_axn_lnks_i:1:odec_axn_lnks_axn')]"));

	    //Search transaction group and verify 
		addElement("textBoxPropCode", By.xpath("//input[contains(@id,'oc_pnl_lstng_vw_srch_swtchr:dc_s2:fe2:lov1:odec_lov_itLovetext::content')]"));
		addElement("listPropRevenue", By.xpath("//select[contains(@id,'oc_pnl_tmpl:oc_pnl_lstng_vw_srch_swtchr:dc_s2:fe3:lov3:odec_lov_socLov::content')]"));
		addElement("buttonSearch", By.xpath("//div[contains(@id,'oc_pnl_lstng_vw_srch_swtchr:dc_s2:odec_srch_swtchr_advncd_srch_btn')]"));

		addElement("tableTransactionGroup", By.xpath("//div[contains(@id,'pt1:oc_pnl_lst_cmp:oc_pnl_lstng_tmpl:oc_pnl_tmpl:pc1:t1::db')]//table"));
        
		addElement("linkOptions", By.xpath("//a[text()='Options']"));

		
	}

	@Override
	public void verifyTitle(ValidationType vType) 
	{
		// TODO Auto-generated method stub
		verifyMessage(vType, "spanTransactionGroups", "Transaction Groups");
	}

	public void enterTransactionGroupInfo(DataProvider dp)
	{		
		Report.log(Status.INFO, "Entering Transaction group Info: ");
		getElement("textBoxCode").sendKeys(dp.get("TransactionGroups", "txtCode"));
		getElement("textBoxDescription").sendKeys(dp.get("TransactionGroups", "txtDescription"));
		getElement("linkSeq").click();	
	}
	
	public void clickSaveButton()
	{		
		getElement("buttonTemplateSave").click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void clickBackToTransactionGroups()
	{
		getElement("linkBackToTransactionGroups").click();
	}
	
	public  void clickSearchButton()
	{
		getElement("linkBackToTransactionGroups").click();
	}
	
	public void searchTransactionGroup(DataProvider dp)
	{
		SeleniumUtils utils=new SeleniumUtils(driver);
		WebElement element=utils.waitForElement(getElementBy("TransactionGroups"), 10);

		element.sendKeys(dp.get("TransactionGroups", "txtCode"));
		getElement("listPropRevenue").select(Status.Index, "1");
	}
	
	public void clickTemplateNew()
	{
		//getElement("linkTemplateNew").ajaxWait(WaitType.VISIBLE);
		//getElement("linkTemplateNew").ajaxWait(WaitType.PRESENCE);
		//WebDriverWait wait=new WebDriverWait(driver, TimeUnit.SECONDS);
		
		SeleniumUtils utils=new SeleniumUtils(driver);
		WebElement element=utils.waitForElement(By.xpath("//div[@id='pt1:oc_pg_pt:pt_pt1:r1:1:pt1:oc_scrn_tmpl:template']//span[text()='New']"), 10);
	//	getElement("linkOptions").ajaxWait(WaitType.VISIBLE);
		//getElement("linkTemplateNew").click();
	//	((JavascriptExecutor)driver).executeScript("arguments[0].click();", getElement("linkTemplateNew").getWebElement());
		element.click();
	}
	
	public void clickTemplate()
	{
		getElement("linkTemplate").click();
	}
	
	public void clickPropertyTab()
	{
		//SeleniumUtils utils=new SeleniumUtils(driver);
		//WebElement element=utils.waitForElement(getElementBy("linkProperty"), 10);		
		getElement("linkProperty").click();
	}
	
	public void clickCopyButton()
	{
		SeleniumUtils utils=new SeleniumUtils(driver);
		WebElement element=utils.waitForElement(getElementBy("linkCopy"), 10);
		element.click();
	}
	
	public void configureParameters(DataProvider dp)
	{
		SeleniumUtils utils=new SeleniumUtils(driver);
		WebElement element=utils.waitForElement(getElementBy("textBoxTransactionGroup"), 10);	
		element.sendKeys(dp.get("TransactionGroups", "txtCode"));
	//	getElement("textBoxTransactionGroup").sendKeys(Keys.TAB);
		getElement("buttonSave").click();		
	}
	
	public void clickCopyAndContinue()
	{
		getElement("buttonCopyAndContinue").click();	
	}
	
	
	
}
