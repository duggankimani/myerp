package com.duggankimani.app.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;

public class BaseAction<T extends BaseActionResult> extends ActionImpl<T>{
	
	private int ErrorCode=0;
	
	private String StatusMessage=null;
	
	private int errorId;
	
	@Override
	public boolean isSecured() {
		return false;
	}

	public int getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}

	public String getStatusMessage() {
		return StatusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

}
