package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.WindowModel;

public class GetWindowActionResult extends BaseActionResult {

	private WindowModel windowModel;
	
	public GetWindowActionResult() {
		
	}
	
	public GetWindowActionResult(WindowModel window){
		this.windowModel = window;
	}

	public WindowModel getWindowModel() {
		return windowModel;
	}

	public void setWindowModel(WindowModel windowModel) {
		this.windowModel = windowModel;
	}
}
