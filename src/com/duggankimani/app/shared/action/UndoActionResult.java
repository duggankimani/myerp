package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.DataModel;

public class UndoActionResult extends BaseActionResult {

	private DataModel data;

	public UndoActionResult() {
		
	}

	public UndoActionResult(DataModel data) {
		this.data = data;
	}

	public DataModel getData() {
		return data;
	}

	public void setData(DataModel data) {
		this.data = data;
	}
}
