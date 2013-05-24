package com.duggankimani.app.server.handlers;

import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.GetMenuAction;
import com.duggankimani.app.shared.action.GetMenuActionResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlerutils.TreeHelper.*;

/**
 * 
 * @author duggan
 *
 */
public class GetMenuActionHandler extends BaseActionHandler<GetMenuAction, GetMenuActionResult>{

	@Inject
	public GetMenuActionHandler() {
	}

	@Override
	public GetMenuActionResult execute(GetMenuAction action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {
		
		GetMenuActionResult result = (GetMenuActionResult)actionResult;		
		
		result.setMenus(getMenu());

		return result;
	}

	@Override
	public void undo(GetMenuAction action, GetMenuActionResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<GetMenuAction> getActionType() {
		return GetMenuAction.class;
	}

}
