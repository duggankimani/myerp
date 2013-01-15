package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;

public class MinTabModel implements Serializable {

	private static final long serialVersionUID = -3287494018092921779L;
	private Integer tabNo;
	private String name;

	public MinTabModel() {
	}
	
	public MinTabModel(Integer tabNo, String name){
		this.tabNo = tabNo;
		this.name = name;
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
}
