package com.duggankimani.app.server.handlers;

import org.compiere.model.GridTab;

import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.CreateRecordAction;
import com.duggankimani.app.shared.action.CreateRecordActionResult;
import com.duggankimani.app.shared.model.DataModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlerutils.DataReader.*;

/**
 * 
 * @author duggan
 *
 */
public class CreateRecordActionHandler extends BaseActionHandler<CreateRecordAction, CreateRecordActionResult>{

	@Inject
	public CreateRecordActionHandler() {
	}


	@Override
	public CreateRecordActionResult execute(CreateRecordAction action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {

		CreateRecordActionResult result = (CreateRecordActionResult)actionResult;
		
		WindowStatus ws = WindowStatus.getWindowStatus(action.getWindowId());
		GridTab curTab = ws.getTab(action.getTabNo());
		
		if(action.getCurrentRow()!=null)
			curTab.navigate(action.getCurrentRow());
		
		curTab.dataNew(false);
		
		DataModel data = getRowData(curTab, false);
		
		result.setData(data);
		
		return result;
	}

	@Override
	public void undo(CreateRecordAction action, CreateRecordActionResult result,
			ExecutionContext context) throws ActionException {
		
	}

	@Override
	public Class<CreateRecordAction> getActionType() {
		return CreateRecordAction.class;
	}

}
