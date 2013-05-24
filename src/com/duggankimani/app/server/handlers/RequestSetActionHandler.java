package com.duggankimani.app.server.handlers;

import java.util.List;

import com.duggankimani.app.shared.action.BaseAction;
import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.RequestSet;
import com.duggankimani.app.shared.action.RequestSetResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RequestSetActionHandler extends
		BaseActionHandler<RequestSet, RequestSetResult> {

	@Inject
	public RequestSetActionHandler() {
	}

	@Override
	public RequestSetResult execute(RequestSet actionSet,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {

		List<BaseAction<BaseActionResult>> actions = actionSet.getActions();
		RequestSetResult results = (RequestSetResult) actionResult;
		
		try {
			for (BaseAction<BaseActionResult> action : actions) {
				results.add(execContext.execute(action));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		

		return results;
	}

	@Override
	public void undo(RequestSet action, RequestSetResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RequestSet> getActionType() {
		return RequestSet.class;
	}

}
