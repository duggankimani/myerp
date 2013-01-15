package com.duggankimani.app.shared.action;

public class GetWindowAction extends BaseERPAction<GetWindowActionResult> {
	Integer AD_Menu_ID;
	Integer WindowID;
	Integer tabNo;
	Integer RecordID;

	@SuppressWarnings("unused")
	private GetWindowAction() {
		// For serialization only
	}

	public GetWindowAction(Integer AD_Menu_ID) {
		this.AD_Menu_ID = AD_Menu_ID;
	}

	public GetWindowAction(Integer AD_Menu_ID, Integer WindowId, Integer tabNo, Integer RecordID) {
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
