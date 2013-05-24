package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.ProcessModel;

public class GetProcessActionResult extends BaseActionResult {

	private ProcessModel processModel;
	
	public GetProcessActionResult() {
	}

	public ProcessModel getProcessModel() {
		return processModel;
	}

	public void setProcessModel(ProcessModel processModel) {
		this.processModel = processModel;
	}
}
