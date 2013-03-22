package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.action.UndoActionResult;
import java.lang.Integer;

public class UndoAction extends BaseAction<UndoActionResult> {

	private Integer windowId;
	private Integer tabNo;
	private Integer previousRowNo;

	@SuppressWarnings("unused")
	private UndoAction() {
		// For serialization only
	}

	public UndoAction(Integer windowId, Integer tabNo, Integer previousRowNo) {
		this.windowId = windowId;
		this.tabNo = tabNo;
		this.previousRowNo = previousRowNo;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public Integer getPreviousRowNo() {
		return previousRowNo;
	}
}
