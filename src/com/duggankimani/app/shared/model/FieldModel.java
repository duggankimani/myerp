package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

public class FieldModel implements Serializable {

	private static final long serialVersionUID = 5834912522352271773L;
	private String name;
	private String columnName;
	private Integer width;
	private boolean isSameLine;
	private DisplayType displayType;
	private String description;
	private Boolean isKeyColumn;
	private int colSpan=1;
	private boolean isDisplayed;
	private int windowId;
	private int tabNo;
	private ArrayList<LookupValue> lookupValues;
	private boolean hasCallout;
	private boolean hasDynamicValidation;
	
	public FieldModel() {
		//defaults
		this.displayType = DisplayType.TEXT;
		this.isKeyColumn=false;
		this.width=100;
		this.isSameLine=false;
		this.hasCallout=false;
		this.hasDynamicValidation=false;
	}

	public FieldModel(String name){
		this();
		this.name = name;		
	}
	
	public FieldModel(String name, DisplayType type) {
		this(name);
		this.displayType = type;
	}
	
	public FieldModel(String name, DisplayType type, boolean sameLine){
		this(name, type);
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

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Boolean isKeyColumn(){
		return isKeyColumn;
	}
	
	public void setKeyColumn(Boolean isKeyColumn){
		this.isKeyColumn = isKeyColumn;
	}
	
	public void setDisplayed(boolean displayed){
		this.isDisplayed = displayed;
	}
	
	public Boolean isDisplayed(){
		return isDisplayed;
	}
	
	public static FieldModel get(String columnName,String name, DisplayType type, boolean sameLine, int width){
		
		FieldModel field = new FieldModel(name, type, sameLine);
		field.setColumnName(columnName);
		field.setWidth(width);
		
		return field;
	}

	public int getWindowId() {
		return windowId;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	public int getTabNo() {
		return tabNo;
	}

	public void setTabNo(int tabNo) {
		this.tabNo = tabNo;
	}

	public boolean hasCallout() {
		return hasCallout;
	}

	public void setHasCallout(boolean hasCallout) {
		this.hasCallout = hasCallout;
	}

	public boolean hasDynamicValidation() {
		return hasDynamicValidation;
	}

	public void setHasDynamicValidation(boolean hasDynamicValidation) {
		this.hasDynamicValidation = hasDynamicValidation;
	}
}
