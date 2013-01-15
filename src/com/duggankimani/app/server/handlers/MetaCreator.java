package com.duggankimani.app.server.handlers;

import java.util.ArrayList;

import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
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

	public WindowModel getWindowModel(Integer AD_Menu_ID) {
		WindowModel model = new WindowModel();

		MMenu menu = new MMenu(Env.getCtx(), AD_Menu_ID, null);

		if (menu.getAD_Window_ID() == 0)
			throw new RuntimeException("No Window for AD_Menu = " + AD_Menu_ID);

		GridWindowVO gridWindowVO = GridWindowVO.create(Env.getCtx(),
				ERPSessionManager.generateWindowNo(), menu.getAD_Window_ID());

		if (gridWindowVO == null)
			throw new RuntimeException("Could not create GridWindowVO is null");

		WindowStatus.getWindowStatus(gridWindowVO, false);
		
		model.setName(gridWindowVO.Name);
		model.setMenuID(AD_Menu_ID);
		model.setDescription(gridWindowVO.Description);
		model.setWindowID(gridWindowVO.AD_Window_ID);

		addTabs(model, gridWindowVO);

		return model;

	}

	private void addTabs(WindowModel model, GridWindowVO gridWindowVO) {

		for (int i = 0; i < gridWindowVO.Tabs.size(); i++) {
			GridTabVO gridTabVO = (GridTabVO) gridWindowVO.Tabs.get(i);

			TabModel tab = new TabModel();
			tab.setName(gridTabVO.Name);
			tab.setTabNo(gridTabVO.TabNo);
			tab.setWindowID(gridTabVO.AD_Window_ID);

			addFields(tab, gridTabVO);
			model.add(tab);
			if(i==2){//load tab 0 and 1 only : tab loading optimization required
				setMinTabDetails(model, gridWindowVO);
				break;
			}
				
		}

	}

	/**
	 * 
	 * @param model
	 * @param gridWindowVO
	 */
	private void setMinTabDetails(WindowModel model, GridWindowVO gridWindowVO) {
		ArrayList<MinTabModel> tabs = new ArrayList<MinTabModel>();
		for(int i=1; i<gridWindowVO.Tabs.size(); i++){
			
			MinTabModel tabModel = new MinTabModel(gridWindowVO.Tabs.get(i).TabNo, gridWindowVO.Tabs.get(i).Name);
			tabs.add(tabModel);
		}
		
		model.setMinTabDetails(tabs);
		
	}

	private void addFields(TabModel tab, GridTabVO gridTabVO) {

		for (GridFieldVO field : gridTabVO.getFields()) {

			FieldModel model = new FieldModel(field.Header, DisplayType.TEXT,
					field.IsSameLine);
			model.setDisplayType(DisplayType.getDisplayType(field.displayType));
			model.setDescription(field.Description);
			model.setColumnName(field.ColumnName);
			model.setDisplayed(field.IsDisplayed);
			model.setColSpan(1);
			if(field.FieldLength>0 && field.FieldLength>60){
				int colSpan = field.FieldLength/60;
				
				if(colSpan>3)
					colSpan=3;
				
				model.setColSpan(colSpan);
			}
			if (model.getDisplayType().isLookup()) {
				loadLookup(field, model);
			}
			
			if(field.IsKey){
				model.setKeyColumn(true);
			}
			tab.addField(model);
		}

	}

	private void loadLookup(GridFieldVO field, FieldModel model) {
		GridField gridField = new GridField(field);

		Lookup lookup = gridField.getLookup();
		if(lookup==null)
			return;
		
		boolean mandatory = gridField.isMandatory(false);
		boolean onlyValidated = true;
		boolean onlyActive = !gridField.isReadOnly();
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

		model.setLookupValues(lookupList);

	}
	
	protected TabModel getMinTabModel(Integer AD_Window_ID, Integer tabNo) {

		GridWindowVO vo = WindowStatus.getWindowStatus(AD_Window_ID).gridWindowV0;
		
		GridTabVO tabVO = vo.Tabs.get(tabNo);


		TabModel model = new TabModel();
		model.setName(tabVO.Name);
		model.setTabNo(tabNo);
		model.setWindowID(AD_Window_ID);
		
		addMinFieldsDetails(model, tabVO);
		
		return model;
	}

	private void addMinFieldsDetails(TabModel tab, GridTabVO tabVO) {
		
		for (GridFieldVO field : tabVO.getFields()) {
			FieldModel model = new FieldModel(field.Header, DisplayType.TEXT,
					field.IsSameLine);
			model.setDisplayType(DisplayType.getDisplayType(field.displayType));
			model.setDescription(field.Description);
			model.setColumnName(field.ColumnName);
			
			if(field.IsKey){
				model.setKeyColumn(true);
			}else if(field.ColumnName.equalsIgnoreCase(tabVO.TableName+"_ID")){
				model.setKeyColumn(true);
			}
			tab.addField(model);
		}

	}

}
