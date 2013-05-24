package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.DataModel;

public class ExecCalloutResult extends BaseActionResult {

	private DataModel data;
	
	public ExecCalloutResult() {
	}
	
	public ExecCalloutResult(DataModel data) {
		this.data = data;
	}

	public DataModel getData() {
		return data;
	}

	public void setData(DataModel data) {
		this.data = data;
	}
	
}
