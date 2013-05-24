package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.action.RequestSetResult;
import java.util.List;

/**
 * List<BaseAction>
 * @author duggan
 *
 */
public class RequestSet extends BaseAction<RequestSetResult> {

	private List<BaseAction<BaseActionResult>> actions;

	@SuppressWarnings("unused")
	private RequestSet() {
		// For serialization only
	}

	public RequestSet(List<BaseAction<BaseActionResult>> actions) {
		this.actions = actions;
	}

	public List<BaseAction<BaseActionResult>> getActions() {
		return actions;
	}
	
	@Override
	public BaseActionResult createDefaultActionResponse() {
		
		return new RequestSetResult();
	}
}
