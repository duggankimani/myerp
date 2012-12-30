package com.duggankimani.app.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import com.duggankimani.app.shared.action.GetWindowActionResult;
import java.lang.Integer;

public class GetWindowAction extends ActionImpl<GetWindowActionResult> {

	Integer AD_Menu_ID;

	@SuppressWarnings("unused")
	private GetWindowAction() {
		// For serialization only
	}

	public GetWindowAction(Integer AD_Menu_ID) {
		this.AD_Menu_ID = AD_Menu_ID;
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	public Integer getAD_Menu_ID() {
		return AD_Menu_ID;
	}

	public void setAD_Menu_ID(Integer aD_Menu_ID) {
		AD_Menu_ID = aD_Menu_ID;
	}
}
