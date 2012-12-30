package com.duggankimani.app.shared.model;

import java.io.Serializable;
import java.lang.String;
import java.lang.Boolean;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author duggan
 *
 */
public class MenuFolder implements Serializable {

	private static final long serialVersionUID = -7464039542426334238L;
	
	private String name;
	private String description;
	private Integer id; 
	private Boolean isLeaf;
	private MenuType menuType;
	private List<MenuFolder> child = new ArrayList<MenuFolder>();
	
	public MenuFolder() {
		setLeaf(false);
	}
	
	public MenuFolder(String name, String description){
		this();
		//setType(MenuType.WINDOW);
		this.name=name;
		this.description= description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Boolean IsLeaf() {
		return isLeaf;
	}
	
	public void addChildMenu(MenuFolder folder){
		child.add(folder);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<MenuFolder> getChildren() {
		return child;
	}

	public void setChildren(List<MenuFolder> child) {
		this.child = child;
	}
	
	public int getChildCount(){
		return child.size();
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType type) {
		this.menuType = type;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}
