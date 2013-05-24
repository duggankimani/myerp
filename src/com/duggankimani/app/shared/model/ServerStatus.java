package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

/**
 * Execution Status info
 * 
 * @author duggan
 *
 */
public class ServerStatus implements Serializable {

	private static final long serialVersionUID = -7889918400967251719L;
	private Boolean isWarning=false;
	private Boolean isError=false;
	private Boolean isConfirmed=false;
	private Boolean isInserting=false;
	private Boolean isChanged=false;
	private Boolean isFirstRow=true;
	private Boolean isLastRow=true;
	private Boolean isLoading=false;
	private Integer totalRows=0;
	private Integer loadedRows=0;
	private String message;
	private String info;
	private String statusLine;
	private String dbStatus;
	private Integer tableId;
	private Integer recordId;
	private Integer windowId;
	private Integer windowNo;
	private Integer tabNo;
	private Boolean isReadOnly;

	public ServerStatus() {
	}

	public Boolean isWarning() {
		return isWarning;
	}

	public void setWarning(Boolean isWarning) {
		this.isWarning = isWarning;
	}

	public Boolean isError() {
		return isError;
	}

	public void setError(Boolean isError) {
		this.isError = isError;
	}

	public Boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Boolean isInserting() {
		return isInserting;
	}

	public void setInserting(Boolean isInserting) {
		this.isInserting = isInserting;
	}

	public Boolean isChanged() {
		return isChanged;
	}

	public void setChanged(Boolean isChanged) {
		this.isChanged = isChanged;
	}

	public Boolean isFirstRow() {
		return isFirstRow;
	}

	public void setFirstRow(Boolean isFirstRow) {
		if(isFirstRow==null){
			this.isFirstRow=true;
		}
		this.isFirstRow = isFirstRow;
	}

	public Boolean isLastRow() {
		return isLastRow;
	}

	public void setLastRow(Boolean isLastRow) {
		if(isLastRow==null){
			this.isLastRow=true;
		}
		this.isLastRow = isLastRow;
	}

	public Boolean isLoading() {
		return isLoading;
	}

	public void setLoading(Boolean isLoading) {
		this.isLoading = isLoading;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getLoadedRows() {
		return loadedRows;
	}

	public void setLoadedRows(Integer loadedRows) {
		this.loadedRows = loadedRows;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	public String getDbStatus() {
		return dbStatus;
	}

	public void setDbStatus(String dbStatus) {
		this.dbStatus = dbStatus;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public void setWindowId(Integer windowId) {
		this.windowId = windowId;
	}

	public Integer getWindowNo() {
		return windowNo;
	}

	public void setWindowNo(Integer windowNo) {
		this.windowNo = windowNo;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public Boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(Boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

}
