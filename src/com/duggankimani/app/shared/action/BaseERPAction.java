package com.duggankimani.app.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;

public class BaseERPAction<T extends BaseERPActionResult> extends ActionImpl<T>{
	
	@Override
	public boolean isSecured() {
		return false;
	}

}
