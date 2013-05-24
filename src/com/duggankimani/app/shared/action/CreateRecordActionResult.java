package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.DataModel;

public class CreateRecordActionResult extends BaseActionResult {

	private DataModel data;

	public CreateRecordActionResult() {
		// For serialization only
	}

	public CreateRecordActionResult(DataModel data) {
		this.data = data;
	}

	public DataModel getData() {
		return data;
	}

	public void setData(DataModel data) {
		this.data = data;
	}
}
