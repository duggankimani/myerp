package com.duggankimani.app.shared.action;

public class GetWindowAction extends BaseAction<GetWindowActionResult> {
	Integer AD_Menu_ID;
	Integer WindowID;
	Integer tabNo;
	Integer RecordID;
	Integer rowNo;

	@SuppressWarnings("unused")
	private GetWindowAction() {
		// For serialization only
	}

	public GetWindowAction(Integer AD_Menu_ID) {
		this.AD_Menu_ID = AD_Menu_ID;
		this.rowNo=-1;
	}

	public GetWindowAction(Integer AD_Menu_ID, Integer WindowId, Integer tabNo, Integer RecordID, Integer rowNo) {
		this.AD_Menu_ID = AD_Menu_ID;
		this.WindowID=WindowId;
		this.tabNo = tabNo;
		this.RecordID=RecordID;
		this.rowNo=rowNo;
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

	public Integer getWindowID() {
		return WindowID;
	}

	public void setWindowID(Integer windowID) {
		WindowID = windowID;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}
}
