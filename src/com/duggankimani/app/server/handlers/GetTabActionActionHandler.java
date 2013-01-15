package com.duggankimani.app.server.handlers;

import org.compiere.model.GridTabVO;
import org.compiere.model.GridWindowVO;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.GetTabAction;
import com.duggankimani.app.shared.action.GetTabActionResult;
import com.duggankimani.app.shared.model.TabModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTabActionActionHandler extends MetaCreator implements
		ActionHandler<GetTabAction, GetTabActionResult> {

	@Inject
	public GetTabActionActionHandler() {
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
