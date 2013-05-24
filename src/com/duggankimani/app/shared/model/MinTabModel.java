package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;

public class MinTabModel implements Serializable {

	private static final long serialVersionUID = -3287494018092921779L;
	private Integer tabNo;
	private String name;
	private Integer windowId;

	public MinTabModel() {
	}
	
	public MinTabModel(Integer tabNo, String name, int windowId){
		this.tabNo = tabNo;
		this.name = name;
		this.windowId = windowId;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public void setWindowId(Integer windowId) {
		this.windowId = windowId;
	}
}
