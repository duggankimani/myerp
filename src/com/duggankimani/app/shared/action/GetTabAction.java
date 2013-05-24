package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.action.GetTabActionResult;
import java.lang.Integer;

public class GetTabAction extends BaseAction<GetTabActionResult> {

	private Integer windowID;
	private Integer tabNo;

	@SuppressWarnings("unused")
	private GetTabAction() {
		// For serialization only
	}

	public GetTabAction(Integer windowID, Integer tabNo) {
		this.windowID = windowID;
		this.tabNo = tabNo;
	}
	
	@Override
	public BaseActionResult createDefaultActionResponse() {
		
		return new GetTabActionResult();
	}

	public Integer getWindowID() {
		return windowID;
	}

	public Integer getTabNo() {
		return tabNo;
	}
}
