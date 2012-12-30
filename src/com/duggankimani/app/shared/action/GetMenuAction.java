package com.duggankimani.app.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import com.duggankimani.app.shared.action.GetMenuActionResult;

public class GetMenuAction extends ActionImpl<GetMenuActionResult> {

	public GetMenuAction() {
	}
	
	@Override
	public boolean isSecured() {

		return false;
	}
}
