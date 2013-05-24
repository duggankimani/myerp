package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
import java.util.ArrayList;


public class TabModel implements Serializable {

	private static final long serialVersionUID = 1512193230969988764L;
	private String name;
	private Integer tabNo;
	private Integer WindowID;
	private Integer windowNo;
	private String keyColumnName;
	private Integer tabLevel;

	private ArrayList<FieldModel> fields = new ArrayList<FieldModel>();
	
	public TabModel() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public void setWindowID(Integer WindowID) {
		this.WindowID = WindowID;
	}

	public String getName() {
		return name;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public Integer getWindowID() {
		return WindowID;
	}
	
	public void addField(FieldModel field){
		fields.add(field);
	}
	
	public ArrayList<FieldModel> getFields(){
		return fields;
	}

	public String getKeyColumnName() {
		return keyColumnName;
	}

	public void setKeyColumnName(String keyColumnName) {
		this.keyColumnName = keyColumnName;
	}
	
	@Override
	public String toString() {
		String str = "WindowID= "+WindowID+", TabNo="+tabNo+", Name="+name;
		return str;
	}

	public Integer getTabLevel() {
		return tabLevel;
	}

	public void setTabLevel(Integer tabLevel) {
		this.tabLevel = tabLevel;
	}

	public Integer getWindowNo() {
		return windowNo;
	}

	public void setWindowNo(Integer windowNo) {
		this.windowNo = windowNo;
	}

	public void setFields(ArrayList<FieldModel> fields) {
		this.fields = fields;
	}
}
