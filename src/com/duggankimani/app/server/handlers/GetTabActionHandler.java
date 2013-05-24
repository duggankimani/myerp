package com.duggankimani.app.server.handlers;

import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.GetTabAction;
import com.duggankimani.app.shared.action.GetTabActionResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlerutils.MetaCreator.*;

/**
 * 
 * @author duggan
 *
 */
public class GetTabActionHandler extends BaseActionHandler<GetTabAction, GetTabActionResult>{

	@Inject
	public GetTabActionHandler() {
	}

	@Override
	public GetTabActionResult execute(GetTabAction action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {
		
		GetTabActionResult result =  (GetTabActionResult)actionResult;
		
		result.setTabModel(getMinTabModel(action.getWindowID(), action.getTabNo()));
		
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
