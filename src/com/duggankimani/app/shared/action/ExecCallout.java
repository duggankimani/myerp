package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.action.ExecCalloutResult;
import java.lang.Integer;
import java.lang.String;
import com.duggankimani.app.shared.model.DataModel;

public class ExecCallout extends BaseAction<ExecCalloutResult> {

	private Integer windowId;
	private Integer tabNo;
	private String fieldName;
	private DataModel data;

	@SuppressWarnings("unused")
	private ExecCallout() {
		// For serialization only
	}

	@Override
	public BaseActionResult createDefaultActionResponse() {
		return new ExecCalloutResult();
	}
	public ExecCallout(Integer windowId, Integer tabNo, String fieldName,
			DataModel data) {
		this.windowId = windowId;
		this.tabNo = tabNo;
		this.fieldName = fieldName;
		this.data = data;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public String getFieldName() {
		return fieldName;
	}

	public DataModel getData() {
		return data;
	}
}
