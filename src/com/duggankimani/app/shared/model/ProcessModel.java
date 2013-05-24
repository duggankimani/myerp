package com.duggankimani.app.shared.model;

public class ProcessModel extends TabModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer processId;
	
	private boolean isProcess;
	
	private boolean isReport;

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public boolean isProcess() {
		return isProcess;
	}

	public void setProcess(boolean isProcess) {
		this.isProcess = isProcess;
	}

	public boolean isReport() {
		return isReport;
	}

	public void setReport(boolean isReport) {
		this.isReport = isReport;
	}
	
}
