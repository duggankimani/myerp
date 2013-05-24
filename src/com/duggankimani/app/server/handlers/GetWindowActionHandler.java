package com.duggankimani.app.server.handlers;

import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.shared.action.GetWindowActionResult;
import com.duggankimani.app.shared.model.WindowModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlerutils.MetaCreator.*;

/**
 * 
 * @author duggan
 *
 */
public class GetWindowActionHandler extends BaseActionHandler<GetWindowAction, GetWindowActionResult>{

	@Inject
	public GetWindowActionHandler() {
	}

	@Override
	public GetWindowActionResult execute(GetWindowAction action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {

		GetWindowActionResult result = (GetWindowActionResult)actionResult;
		
		WindowModel model = getWindowModel(action.getAD_Menu_ID(),action.getWindowID(), action.getTabNo());

		result.setWindowModel(model);
		
		return result;
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
