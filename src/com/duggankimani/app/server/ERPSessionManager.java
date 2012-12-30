package com.duggankimani.app.server;

import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.ContextProvider;

public class ERPSessionManager implements ContextProvider{

	static final Properties props;

	static {
		
		props = new Properties();
		
		props.setProperty("#AD_Client_ID","11");
		props.setProperty("#AD_Client_Name","GardenWorld");
		props.setProperty("#AD_Language","en_US");
		props.setProperty("#AD_Org_ID","0");
		props.setProperty("#AD_Org_Name","*");
		props.setProperty("#AD_PrintColor_ID","100");
		props.setProperty("#AD_PrintFont_ID","130");
		props.setProperty("#AD_PrintPaper_ID","100");
		props.setProperty("#AD_PrintTableFormat_ID","100");
		props.setProperty("#AD_Role_ID","102");
		props.setProperty("#AD_Role_Name","GardenWorld Admin");
		props.setProperty("#AD_SearchDefinition_ID","50000");
		props.setProperty("#AD_Session_ID","1000000");
		props.setProperty("#AD_User_ID","100");
		props.setProperty("#AD_User_Name","SuperUser");
		props.setProperty("#C_BP_Group_ID","103");
		props.setProperty("#C_BankAccount_ID","100");
		props.setProperty("#C_CashBook_ID","101");
		props.setProperty("#C_ConversionType_ID","114");
		props.setProperty("#C_Country_ID","100");
		props.setProperty("#C_DocTypeTarget_ID","126");
		props.setProperty("#C_Dunning_ID","100");
		props.setProperty("#C_PaymentTerm_ID","105");
		props.setProperty("#C_Region_ID","142");
		props.setProperty("#C_TaxCategory_ID","107");
		props.setProperty("#C_Tax_ID","104");
		props.setProperty("#C_UOM_ID","100");
		props.setProperty("#Date","2012-12-29 00:00:00");
		props.setProperty("#GL_Category_ID","108");
		props.setProperty("#IsLiberoEnabled","Y");
		props.setProperty("#M_Locator_ID","50006");
		props.setProperty("#M_PriceList_ID","101");
		props.setProperty("#M_Product_Category_ID","105");
		props.setProperty("#Printer","minolta");
		props.setProperty("#R_StatusCategory_ID","100");
		props.setProperty("#R_Status_ID","100");
		props.setProperty("#SalesRep_ID","100");
		props.setProperty("#ShowAcct","Y");
		props.setProperty("#ShowAdvanced","Y");
		props.setProperty("#ShowTrl","N");
		props.setProperty("#StdPrecision","2");
		props.setProperty("#SysAdmin","Y");
		props.setProperty("#User_Level"," CO");
		props.setProperty("#User_Org","0,11,12,50000,50001,50002,50004,50005,50006,50007");
		props.setProperty("#YYYY","Y");
		props.setProperty("$C_AcctSchema_ID","101");
		props.setProperty("$C_Currency_ID","100");
		props.setProperty("$Element_AC","Y");
		props.setProperty("$Element_BP","Y");
		props.setProperty("$Element_MC","Y");
		props.setProperty("$Element_OO","Y");
		props.setProperty("$Element_PJ","Y");
		props.setProperty("$Element_PR","Y");
		props.setProperty("$HasAlias","Y");
		props.setProperty("0|WindowName","&Menu");
		props.setProperty("AutoCommit","Y");
		props.setProperty("AutoNew","N");
		props.setProperty("P103|Type","S");
		props.setProperty("P132|GL_Category_ID","108");
		props.setProperty("P143|C_DocTypeTarget_ID","135");
		props.setProperty("P167|C_DocTypeTarget_ID","116");
		props.setProperty("P181|C_DocTypeTarget_ID","126");
		props.setProperty("P183|C_DocTypeTarget_ID","123");
		props.setProperty("P184|C_DocType_ID","122");
		props.setProperty("P189|DataType","S");
		props.setProperty("P194|C_Charge_ID","100");
		props.setProperty("P195|C_DocType_ID","119");
		props.setProperty("P53004|AD_Org_ID","0");
		props.setProperty("P53005|Action","C");
		props.setProperty("P53032|AD_Org_ID","0");
		props.setProperty("P53034|AD_Org_ID","0");
		props.setProperty("P53035|AD_Org_ID","0");
		props.setProperty("P53044|C_DocType_ID","115");
		props.setProperty("P53046|A_Entry_Type","TRN");
		props.setProperty("P53046|C_DocType_ID","115");
		props.setProperty("P53047|A_Entry_Type","DIS");
		props.setProperty("P53047|C_DocType_ID","115");
		props.setProperty("P53051|A_Entry_Type","SPL");
		props.setProperty("P53051|C_DocType_ID","115");
		props.setProperty("P53051|GL_Category_ID","108");
		props.setProperty("P53053|A_Entry_Type","DEP");
		props.setProperty("P53053|C_DocType_ID","115");
		props.setProperty("P53055|A_Entry_Type","NEW");
		props.setProperty("P53055|C_DocType_ID","115");
		props.setProperty("P|C_Country_ID","100");
		props.setProperty("P|C_ProjectStatus_ID","100");
				
	}

	private static int count = 0;

	public static int generateWindowNo() {
		return ++count;
	}

	@Override
	public Properties getContext() {
		
		return props;
	}

	@Override
	public void showURL(String arg0) {
		// TODO Auto-generated method stub
		
	}
}
