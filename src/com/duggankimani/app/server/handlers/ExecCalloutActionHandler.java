package com.duggankimani.app.server.handlers;

import org.compiere.model.GridTab;

import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.ExecCallout;
import com.duggankimani.app.shared.action.ExecCalloutResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlerutils.DataReader.*;
import static com.duggankimani.app.server.handlerutils.DataWriter.*;

/**
 * 
 * @author duggan
 *
 */
public class ExecCalloutActionHandler extends BaseActionHandler<ExecCallout, ExecCalloutResult>{

	@Inject
	public ExecCalloutActionHandler() {
	}

	@Override
	public ExecCalloutResult execute(ExecCallout action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {
		
		ExecCalloutResult result = (ExecCalloutResult)actionResult;
		
		WindowStatus ws = WindowStatus.getWindowStatus(action.getWindowId());
		GridTab curTab = ws.getTab(action.getTabNo());
		
		//two things- 
		bind(action.getData(), curTab);
				
		result.setData(getRowData(curTab, false));
		 
		return result;
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
