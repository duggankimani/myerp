package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

public class FieldModel implements Serializable {

	private static final long serialVersionUID = 5834912522352271773L;
	private String name;
	private boolean isSameLine;
	private DisplayType displayType;
	private String description;
	private int colSpan=1;
	private ArrayList<LookupValue> lookupValues;

	public FieldModel() {
	}

	public FieldModel(String name){
		this(name, DisplayType.TEXT);
	}
	
	public FieldModel(String name, DisplayType type) {
		this(name,type, false);
	}
	
	public FieldModel(String name, DisplayType type, boolean sameLine){
		this.name = name;
		this.displayType = type;
		this.isSameLine=sameLine;
	}
	
	public FieldModel(String name, DisplayType type, boolean sameLine, int cellspan){
		this(name, type,sameLine);
		this.colSpan=cellspan;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public DisplayType getDisplayType() {
		return displayType;
	}

	public boolean isSameLine() {
		return isSameLine;
	}

	public void setSameLine(boolean isSameLine) {
		this.isSameLine = isSameLine;
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public void setDisplayType(DisplayType displayType) {
		this.displayType = displayType;
	}

	public ArrayList<LookupValue> getLookupValues() {
		return lookupValues;
	}

	public void setLookupValues(ArrayList<LookupValue> lookupValues) {
		this.lookupValues = lookupValues;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
