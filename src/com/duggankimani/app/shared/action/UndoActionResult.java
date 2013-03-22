package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.DataModel;

public class UndoActionResult extends BaseActionResult {

	private DataModel data;

	@SuppressWarnings("unused")
	private UndoActionResult() {
		// For serialization only
	}

	public UndoActionResult(DataModel data) {
		this.data = data;
	}

	public DataModel getData() {
		return data;
	}
}
