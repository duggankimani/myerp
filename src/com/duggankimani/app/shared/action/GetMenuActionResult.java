package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.MenuFolder;
import com.gwtplatform.dispatch.shared.Result;

public class GetMenuActionResult implements Result {

	MenuFolder menus;
	
	public GetMenuActionResult() {
		
	}

	public MenuFolder getMenus() {
		return menus;
	}

	public void setMenus(MenuFolder menus) {
		this.menus = menus;
	}
}
