package com.duggankimani.app.server.handlers;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.shared.action.GetWindowActionResult;
import com.duggankimani.app.shared.model.WindowModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * 
 * @author duggan
 *
 */
public class GetWindowActionHandler extends MetaCreator implements
		ActionHandler<GetWindowAction, GetWindowActionResult> {

	@Inject
	public GetWindowActionHandler() {
	}

	@Override
	public GetWindowActionResult execute(GetWindowAction action,
			ExecutionContext context) throws ActionException {
		
		WindowModel model = getWindowModel(action.getAD_Menu_ID(),action.getWindowID(), action.getTabNo());

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


}
