package com.duggankimani.app.server.handlers;

import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.CreateRecordAction;
import com.duggankimani.app.shared.action.CreateRecordActionResult;
import com.duggankimani.app.shared.model.DataModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlers.DataReader.*;

public class CreateRecordActionHandler extends MetaCreator implements
		ActionHandler<CreateRecordAction, CreateRecordActionResult> {

	@Inject
	public CreateRecordActionHandler() {
	}

	@Override
	public CreateRecordActionResult execute(CreateRecordAction action, ExecutionContext context)
			throws ActionException {
		
		WindowStatus ws = WindowStatus.getWindowStatus(action.getWindowId());
		
		GridWindow window = ws.gridWindow;
		GridTab curTab = window.getTab(action.getTabNo());
		
		if(action.getCurrentRow()!=null)
			curTab.navigate(action.getCurrentRow());
		
		curTab.dataNew(false);
		
		DataModel data = getRowData(curTab, false);
		
		return new CreateRecordActionResult(data);
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
