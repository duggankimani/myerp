package com.duggankimani.app.server.handlers;

import java.util.ArrayList;

import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.GridTabVO;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.Lookup;
import org.compiere.model.MLocator;
import org.compiere.model.MMenu;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.ERPSessionManager;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.shared.action.GetWindowActionResult;
import com.duggankimani.app.shared.model.DisplayType;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.duggankimani.app.shared.model.TabModel;
import com.duggankimani.app.shared.model.WindowModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * 
 * @author duggan
 *
 */
public class GetWindowActionHandler implements
		ActionHandler<GetWindowAction, GetWindowActionResult> {

	@Inject
	public GetWindowActionHandler() {
	}

	@Override
	public GetWindowActionResult execute(GetWindowAction action,
			ExecutionContext context) throws ActionException {
		WindowModel model = getWindowModel(action.getAD_Menu_ID());
		

		GetWindowActionResult actionresult = new GetWindowActionResult(model);

		return actionresult;
	}

	@Override
	public void undo(GetWindowAction action, GetWindowActionResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<GetWindowAction> getActionType() {
		return GetWindowAction.class;
	}

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
			if(i==2)//load tab 0 and 1 only : tab loading optimization required
				break;
				
		}

	}

	private void addFields(TabModel tab, GridTabVO gridTabVO) {

		for (GridFieldVO field : gridTabVO.getFields()) {

			FieldModel model = new FieldModel(field.Header, DisplayType.TEXT,
					field.IsSameLine);
			model.setDisplayType(DisplayType.getDisplayType(field.displayType));
			model.setDescription(field.Description);
			model.setColumnName(field.ColumnName);
			model.setDisplayed(field.IsDisplayed);
			
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

}
