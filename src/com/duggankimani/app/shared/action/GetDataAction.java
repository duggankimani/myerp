package com.duggankimani.app.shared.action;

import java.lang.Integer;

public class GetDataAction extends BaseERPAction<GetDataActionResult> {
	private Integer tabNo;
	private Integer windowNo;
	private Integer WindowID;
	private Integer menuID;
	
	/**
	 * Number of rows to navigate, left or right of the data set
	 */
	private Integer rows;
	private Boolean multipleResults;

	private GetDataAction() {
		// For serialization only
		this.multipleResults=false;
	}

	public GetDataAction(Integer tabNo, Integer windowNo, Integer WindowID, Integer menuID) {
		this();
		this.tabNo = tabNo;
		this.windowNo = windowNo;
		this.WindowID = WindowID;
		this.menuID = menuID;
	}
	
	public GetDataAction(Integer tabNo, Integer windowNo, Integer WindowID, Integer menuID, Boolean isMultipleResults) {
		this(tabNo, windowNo, WindowID, menuID);
		this.multipleResults = isMultipleResults;
	}
	
	public GetDataAction(Integer tabNo, Integer windowNo, Integer WindowID, Integer menuID, Integer rows) {
		this(tabNo, windowNo, WindowID, menuID);
		this.rows = rows;
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

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public void setMultipleRows(Boolean isMultipleResults){
		this.multipleResults = isMultipleResults;
	}
	
	public Boolean isMultipleResults(){
		return multipleResults;
	}
}
