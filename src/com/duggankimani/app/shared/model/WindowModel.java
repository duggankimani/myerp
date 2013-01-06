package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
import java.util.ArrayList;

/**
 * 
 * @author duggan
 *
 */
public class WindowModel implements Serializable {

	private static final long serialVersionUID = 2997284162721880615L;
	private String name;
	private Integer WindowID;
	private String description;
	private Integer MenuID;
	//only tab 0 and tab 1 are saved
	private ArrayList<TabModel> tabs = new ArrayList<TabModel>();
	
	private ArrayList<MinTabModel> tabHeaders = new ArrayList<MinTabModel>();
	
	public WindowModel() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWindowID(Integer WindowID) {
		this.WindowID = WindowID;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMenuID(Integer MenuID) {
		this.MenuID = MenuID;
	}

	public String getName() {
		return name;
	}

	public Integer getWindowID() {
		return WindowID;
	}

	public String getDescription() {
		return description;
	}

	public Integer getMenuID() {
		return MenuID;
	}
	
	public void add(TabModel tab){
		tabs.add(tab);
	}
	
	public ArrayList<TabModel> getTabs(){
		return tabs;
	}
	
	public TabModel getTab(int tabNo){
		return tabs.get(tabNo);
	}
}
