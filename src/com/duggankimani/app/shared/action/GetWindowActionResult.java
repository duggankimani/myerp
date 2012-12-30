package com.duggankimani.app.shared.action;

import com.gwtplatform.dispatch.shared.Result;
import com.duggankimani.app.shared.model.FieldModel;

import java.util.ArrayList;

public class GetWindowActionResult implements Result {

	private ArrayList<FieldModel> fieldModels;
	private Integer windowId;
	private Integer tableId;
	private Integer tabNo;

	@SuppressWarnings("unused")
	private GetWindowActionResult() {
		
	}

	public GetWindowActionResult(ArrayList<FieldModel> fieldModels) {
		this.fieldModels = fieldModels;
	}

	public ArrayList<FieldModel> getFieldModels() {
		return fieldModels;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getTableId() {
		return tableId;
	}

	public Integer getTabNo() {
		return tabNo;
	}
}
