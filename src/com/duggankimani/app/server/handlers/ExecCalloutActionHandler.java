package com.duggankimani.app.server.handlers;

import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.ExecCallout;
import com.duggankimani.app.shared.action.ExecCalloutResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlers.DataWriter.*;
import static com.duggankimani.app.server.handlers.DataReader.*;

public class ExecCalloutActionHandler implements
		ActionHandler<ExecCallout, ExecCalloutResult> {

	@Inject
	public ExecCalloutActionHandler() {
	}

	@Override
	public ExecCalloutResult execute(ExecCallout action, ExecutionContext context)
			throws ActionException {
		WindowStatus ws = WindowStatus.getWindowStatus(action.getWindowId());
		
		GridWindow window = ws.gridWindow;
		GridTab curTab = window.getTab(action.getTabNo());
		
		//two things- 
		bind(action.getData(), curTab);
		
		return new ExecCalloutResult(getRowData(curTab, false)); 
	}

	@Override
	public void undo(ExecCallout action, ExecCalloutResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<ExecCallout> getActionType() {
		return ExecCallout.class;
	}
}
