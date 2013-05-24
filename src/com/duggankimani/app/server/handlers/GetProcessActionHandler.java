package com.duggankimani.app.server.handlers;

import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.GetProcessAction;
import com.duggankimani.app.shared.action.GetProcessActionResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlerutils.ProcessCreator.*;

public class GetProcessActionHandler extends
		BaseActionHandler<GetProcessAction, GetProcessActionResult> {
	

	@Inject
	public GetProcessActionHandler() {
	}


	@Override
	public GetProcessActionResult execute(GetProcessAction action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {
		
		GetProcessActionResult result = (GetProcessActionResult)actionResult;

		result.setProcessModel(getProcessModel(action.getMenuId()));
		
		return result;
	}
	
	@Override
	public void undo(GetProcessAction action, GetProcessActionResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<GetProcessAction> getActionType() {
		return GetProcessAction.class;
	}
}
