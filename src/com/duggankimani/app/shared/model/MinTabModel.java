package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;

public class MinTabModel implements Serializable {

	private static final long serialVersionUID = -3287494018092921779L;
	private String tabNo;
	private String name;

	public MinTabModel() {
	}

	public void setTabNo(String tabNo) {
		this.tabNo = tabNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTabNo() {
		return tabNo;
	}

	public String getName() {
		return name;
	}
}
