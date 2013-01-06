package com.duggankimani.app.shared.action;

import java.lang.Integer;

public class GetDataAction extends BaseERPAction<GetDataActionResult> {
	private Integer tabNo;
	private Integer windowNo;
	private Integer WindowID;
	private Integer menuID;

	@SuppressWarnings("unused")
	private GetDataAction() {
		// For serialization only
	}

	public GetDataAction(Integer tabNo, Integer windowNo, Integer WindowID, Integer menuID) {
		this.tabNo = tabNo;
		this.windowNo = windowNo;
		this.WindowID = WindowID;
		this.menuID = menuID;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public Integer getWindowNo() {
		return windowNo;
	}

	public Integer getWindowID() {
		return WindowID;
	}

	public Integer getMenuID() {
		return menuID;
	}
}
