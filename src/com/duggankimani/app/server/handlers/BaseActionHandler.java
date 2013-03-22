package com.duggankimani.app.server.handlers;

import com.duggankimani.app.shared.action.BaseAction;
import com.duggankimani.app.shared.action.BaseActionResult;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class BaseActionHandler<A extends BaseAction<B>, B extends BaseActionResult> implements ActionHandler<A, B> {

	@Override
	public B execute(A arg0, ExecutionContext arg1) throws ActionException {
		return null;
	}

	@Override
	public Class<A> getActionType() {
		
		return null;
	}

	@Override
	public void undo(A arg0, B arg1, ExecutionContext arg2)
			throws ActionException {
		
	}

	
}
