package com.duggankimani.app.server.handlers;


import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.shared.action.GetTabAction;
import com.duggankimani.app.shared.action.GetTabActionResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTabActionHandler extends MetaCreator implements
		ActionHandler<GetTabAction, GetTabActionResult> {

	@Inject
	public GetTabActionHandler() {
	}

	@Override
	public GetTabActionResult execute(GetTabAction action, ExecutionContext context)
			throws ActionException {
		
		GetTabActionResult result = new GetTabActionResult(getMinTabModel(action.getWindowID(), action.getTabNo()));
		
		return result;
	}


	@Override
	public void undo(GetTabAction action, GetTabActionResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<GetTabAction> getActionType() {
		return GetTabAction.class;
	}
}