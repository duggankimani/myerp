package com.duggankimani.app.server.handlerutils;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.GridTab;
import org.compiere.model.GridTabVO;
import org.compiere.model.GridWindowVO;
import org.compiere.model.Lookup;
import org.compiere.model.MLocator;
import org.compiere.model.MMenu;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

import com.duggankimani.app.server.ERPSessionManager;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.model.DisplayType;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.duggankimani.app.shared.model.MinTabModel;
import com.duggankimani.app.shared.model.TabModel;
import com.duggankimani.app.shared.model.WindowModel;

public class MetaCreator {

	public static WindowModel getWindowModel(Integer AD_Menu_ID) {
		MMenu menu = new MMenu(Env.getCtx(), AD_Menu_ID, null);

		if (menu.getAD_Window_ID() == 0)
			throw new RuntimeException("No Window for AD_Menu = " + AD_Menu_ID);

		
		return getWindowModelByWindowID(menu.getAD_Window_ID(), 0);

	}
	
	public static WindowModel getWindowModel(Integer AD_Menu_ID, Integer AD_Window_ID, Integer tabNo) {
		try{
			if(AD_Window_ID==null || AD_Window_ID <=0){
				return getWindowModel(AD_Menu_ID);
			}
			
			if(tabNo==null){
				tabNo=0;
			}
			
			return getWindowModelByWindowID(AD_Window_ID, tabNo);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	private static WindowModel getWindowModelByWindowID(Integer AD_Window_ID, int tabNo) {
		//TabModel tab = new TabModel();
		WindowStatus ws = WindowStatus.getWindowStatus(AD_Window_ID);
		
		WindowModel model = new WindowModel();
		
		model.setName(ws.gridWindowV0.Name);
		model.setDescription(ws.gridWindowV0.Description);
		model.setWindowID(ws.gridWindowV0.AD_Window_ID);
		model.setWindowNo(ws.gridWindowV0.WindowNo);
		
		addTabs(tabNo, model, ws.gridWindowV0);
		return model;
	}
	
	private static void addTabs(int rootTabNo, WindowModel model, GridWindowVO gridWindowVO){
		
		int rootTabLevel=0;
		
		GridTabVO rootTabVO = (GridTabVO) gridWindowVO.Tabs.get(rootTabNo);
				
		rootTabLevel = rootTabVO.TabLevel;
		//load children of this tab			
		TabModel roottab = new TabModel();
		createTabModel(rootTabVO, roottab);
		
		if(rootTabNo!=0){
			model.setName(rootTabVO.Name);
			model.setWindowID(gridWindowVO.AD_Window_ID);
		}
		addFields(roottab, rootTabVO);
		model.add(roottab);
	
		
		for (int i = rootTabNo+1; i < gridWindowVO.Tabs.size(); i++) {
			
			GridTabVO gridTabVO = (GridTabVO) gridWindowVO.Tabs.get(i);
			
			if(gridTabVO.TabLevel==rootTabLevel){
				//other tabs at the same level as this tab.
				//TODO:Find a way to handle this for tablevel 0 example window- Accounting Dimensions window
				continue;
			}
				
			if(gridTabVO.TabLevel != rootTabLevel+1){
				
				//Not direct children of the root Tab
				continue;
				
			}
						
			if(i==rootTabNo+1){ //next tabNo
				
				//load Full tab definition (This is the first child tab loaded as a grid when the window is first open )			
				TabModel tab = new TabModel();
				createTabModel(gridTabVO, tab);
				addFields(tab, gridTabVO);
				model.add(tab);
				model.addMinTabDetail(new MinTabModel(gridTabVO.TabNo, gridTabVO.Name, gridTabVO.AD_Window_ID));

			}else{
				
				//tab loading optimization required
				//setMinTabDetails(model, gridWindowVO);
				addMinTabDetail(model, gridTabVO);
				//break;
			}
				
		}

	}

	private static GridTab getGridTab(int tabNo, int AD_Window_ID) {
		WindowStatus ws = WindowStatus.getWindowStatus(AD_Window_ID);
		
		if(ws==null){
			return null;
		}
		
		return ws.getTab(tabNo);
		
	}

	private static void createTabModel(GridTabVO gridTabVO, TabModel tab) {
		tab.setName(gridTabVO.Name);
		tab.setTabNo(gridTabVO.TabNo);
		tab.setWindowNo(gridTabVO.WindowNo);
		tab.setWindowID(gridTabVO.AD_Window_ID);
		tab.setTabLevel(gridTabVO.TabLevel);
		
	}
	
	private static void addFields(TabModel tab, GridTabVO gridTabVO) {

		for (GridFieldVO field : gridTabVO.getFields()) {

			FieldModel model = new FieldModel(field.Header, DisplayType.TEXT,
					field.IsSameLine);
			model.setDisplayType(DisplayType.getDisplayType(field.displayType));
			model.setDescription(field.Description);
			model.setColumnName(field.ColumnName);
			model.setDisplayed(field.IsDisplayed);
			model.setColSpan(1);
			model.setWindowId(gridTabVO.AD_Window_ID);
			model.setTabNo(gridTabVO.TabNo);
			model.setMandatory(field.IsMandatory);
//			field.FieldGroup;
//			field.FieldGroupType;
			model.setHasCallout(field.Callout!=null && !field.Callout.isEmpty());
			
			//model.setHasDynamicValidation(field.);
			
			if(field.FieldLength>0 && field.FieldLength>60){
				int colSpan = field.FieldLength/60;
				
				if(colSpan>3)
					colSpan=3;
				
				model.setColSpan(colSpan);
			}
			if (model.getDisplayType().isLookup()) {
				loadLookup(field, model);
			}
			
			if(field.IsKey || field.ColumnName.equalsIgnoreCase(gridTabVO.TableName+"_ID")){
				model.setKeyColumn(true);
				
			}
			tab.addField(model);
		}

	}

	private static void loadLookup(GridFieldVO field, FieldModel model) {
		GridField gridField = new GridField(field);

		Lookup lookup = gridField.getLookup();
		boolean onlyActive = !gridField.isReadOnly();
		boolean mandatory = gridField.isMandatory(false);
		model.setLookupValues(getLookupValues(lookup, mandatory, onlyActive));

	}
	
	public static ArrayList<LookupValue> getLookupValues(Lookup lookup, boolean mandatory, boolean onlyActive){
		if(lookup==null)
			return null;
		
		boolean onlyValidated = true;
		
		boolean temporary = true;

		ArrayList<LookupValue> lookupList = new ArrayList<LookupValue>();

		ArrayList<Object> ar = lookup.getData(mandatory, onlyValidated,
				onlyActive, temporary);

		if (ar != null && ar.size() > 0) {
			Object[] list = ar.toArray();

			for (int j = 0; j < list.length; j++) {
				boolean isNumber = list[0] instanceof KeyNamePair;
				boolean isLocator = list[0] instanceof MLocator;
				LookupValue lookupValue;

				if (isNumber) {
					KeyNamePair p = (KeyNamePair) list[j];
					lookupValue = new LookupValue(p.getKey(), p.getName());
				} else if (isLocator) {
					KeyNamePair p = ((MLocator) list[j]).getKeyNamePair();
					lookupValue = new LookupValue(p.getKey(), p.getName());
				} else {
					ValueNamePair p = (ValueNamePair) list[j];
					lookupValue = new LookupValue(p.getValue(), p.getName());
				}
				lookupList.add(lookupValue);
			}
		}

		return lookupList;
	}

	public static TabModel getMinTabModel(Integer AD_Window_ID, Integer tabNo) {

		WindowStatus ws = WindowStatus.getWindowStatus(AD_Window_ID);
		
		GridTab tab = ws.getTab(tabNo);
		TabModel model = new TabModel();
	
		createMinTabModel(model, tab);
		
		return model;
		
	}
	
	public static void createMinTabModel(TabModel parent, GridTab tab){
		parent.setName(tab.getName());
		parent.setTabNo(tab.getTabNo());
		parent.setWindowID(tab.getAD_Window_ID());
		parent.setWindowNo(tab.getWindowNo());
		parent.setTabLevel(tab.getTabLevel());
	
		addMinFieldsDetails(parent, tab);
	}

	private static void addMinTabDetail(WindowModel model, GridTabVO gridTabV0){
		MinTabModel tabModel = new MinTabModel(gridTabV0.TabNo, gridTabV0.Name, gridTabV0.AD_Window_ID);
		model.addMinTabDetail(tabModel);
	}
	

	private static void addMinFieldsDetails(TabModel tabModel, GridTab tab) {
		
		tabModel.setKeyColumnName(tab.getKeyColumnName());
		
		if(tab.getKeyColumnName()==null || tab.getKeyColumnName().isEmpty()){
			ArrayList<String> parentCols = tab.getParentColumnNames();
			
			if(!parentCols.isEmpty())
			if(parentCols.size()==2){
				tabModel.setKeyColumnName(parentCols.get(0)+"-"+parentCols.get(1));
			}else{
				tabModel.setKeyColumnName(parentCols.get(0)+"-");
			}
		}
		
		for (GridField field : tab.getFields()) {
			if(!field.isDisplayed())
				continue;
			
			FieldModel model = new FieldModel(field.getHeader(), DisplayType.TEXT,
					field.isSameLine());
			model.setDisplayType(DisplayType.getDisplayType(field.getDisplayType()));
			model.setDescription(field.getDescription());
			model.setColumnName(field.getColumnName());
			model.setHasCallout(field.getCallout()!=null && !field.getCallout().isEmpty());
			model.setTabNo(tab.getTabNo());
			model.setWindowId(tab.getAD_Window_ID());
			
			if(field.isKey()){
		
				model.setKeyColumn(true);
				tabModel.setKeyColumnName(field.getColumnName());
			}
			tabModel.addField(model);
		}

	}


}
