package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.action.CreateRecordActionResult;
import java.lang.Integer;

public class CreateRecordAction extends BaseAction<CreateRecordActionResult> {

	private Integer windowId;
	private Integer menuId;
	private Integer currentRow;
	private Integer tabNo;

	@SuppressWarnings("unused")
	private CreateRecordAction() {
		// For serialization only
	}

	public CreateRecordAction(Integer windowId, Integer menuId,Integer tabNo, Integer currentRow) {
		this.windowId = windowId;
		this.menuId = menuId;
		this.currentRow = currentRow;
		this.tabNo = tabNo;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public Integer getCurrentRow() {
		return currentRow;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}
}
