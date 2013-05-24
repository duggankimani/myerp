package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.action.GetProcessActionResult;
import java.lang.Integer;

public class GetProcessAction extends BaseAction<GetProcessActionResult> {

	private Integer windowId;
	private Integer windowNo;
	private Integer tabNo;
	private Integer recordId;
	private Integer rowNo;
	
	
	private Integer menuId;

	@SuppressWarnings("unused")
	private GetProcessAction() {
		// For serialization only
	}

	@Override
	public BaseActionResult createDefaultActionResponse() {
		return new GetProcessActionResult();
	}
	
	public GetProcessAction(Integer menuId){
		this.menuId = menuId;
	}
	
	public GetProcessAction(Integer windowId, Integer windowNo, Integer tabNo,
			Integer recordId, Integer rowNo) {
		this.windowId = windowId;
		this.windowNo = windowNo;
		this.tabNo = tabNo;
		this.recordId = recordId;
		this.rowNo = rowNo;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getWindowNo() {
		return windowNo;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public Integer getRowNo() {
		return rowNo;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}
