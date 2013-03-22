package com.duggankimani.app.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;

public class BaseAction<T extends BaseActionResult> extends ActionImpl<T>{
	
	@Override
	public boolean isSecured() {
		return false;
	}

}
