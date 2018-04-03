package oracle.opera.cloud.admin.pages;

import org.openqa.selenium.By;
import org.oracle.cloud.common.PageObjects;
import org.oracle.cloud.common.ValidationType;
import org.oracle.cloud.common.WrappedWebDriver;

public class TransactionSubGroupPage extends PageObjects
{

	public TransactionSubGroupPage(WrappedWebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
		
		addElement("linkNew", By.linkText("New"));
		addElement("linkTemplate", By.id("pt1:oc_pg_pt:pt_pt1:r1:1:pt1:oc_scrn_tmpl:template::ti"));
		addElement("linkNew", By.linkText("New"));
		addElement("textBoxCode", By.xpath("//input[contains(@id,'fe1:ic1:occ_ic_it:odec_it_it::content')]"));
		addElement("textBoxDescription", By.xpath("//input[contains(@id,'pt_pt1:fe2:it4:odec_it_it::content')]"));
		addElement("textBoxGroup", By.xpath("//input[contains(@id,'pt_pt1:fe4:lov1:odec_lov_itLovetext::content')]"));
		addElement("buttonSave", By.xpath("//div[contains(@id,'oc_pnl_axnbr:odec_axn_br_i:1:odec_axn_br_axn_btn')]"));

		//COPY TO PROPERTY
		addElement("linkCopy", By.xpath("//a[contains(@id,'pt1:oc_pnl_lst_cmp:oc_pnl_lstng_tmpl:oc_pnl_tmpl:oc_pnl_tlbr_axns:odec_axn_lnks_i:1:odec_axn_lnks_axn::text')]"));
		addElement("textBoxAvailable", By.xpath("//a[contains(@id,'p1:occ_pnl:pt_pt1:slov1:odec_slov_avlb_ts:odec_ts_sbfrm:odec_ts_inpt')]"));
		addElement("linkGo", By.xpath("//a[contains(@id,'p1:occ_pnl:pt_pt1:slov1:odec_slov_avlb_ts:odec_ts_sbfrm:odec_ts_srch')]"));

		addElement("tableTransactionGroup", By.xpath("//div[contains(@id,'p1:occ_pnl:pt_pt1:slov1:odec_slov_avlb_pc:odec_slov_avlb_tbl::db')]//table"));
		addElement("buttonSave", By.xpath("//div[contains(@id,'oc_pnl_tmpl:pt_pt1:oc_pnl_axnbr:odec_axn_br_i:0:odec_axn_br_axn_btn')]"));
		addElement("buttonCopyAndContinue", By.xpath("//div[contains(@id,'oc_pnl_tmpl:pt_pt1:oc_pnl_axnbr:odec_axn_br_i:0:odec_axn_br_axn_btn')]"));
		addElement("linkBacktoTransactionGroups", By.xpath("//a[contains(@id,'oc_stps_bc_tlbr_axns:odec_axn_lnks_i:0:odec_axn_lnks_axn')]"));
		addElement("linkBacktoHome", By.xpath("//a[contains(@id,'oc_scrn_bc_tlbr_axns:odec_axn_lnks_i:0:odec_axn_lnks_axn')]"));

		
	}

	

	@Override
	public void verifyTitle(ValidationType vType) {
		// TODO Auto-generated method stub
		
	}

}
