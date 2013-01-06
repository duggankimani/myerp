package com.duggankimani.app.shared.action;

import java.math.BigDecimal;
import java.util.Date;

import com.duggankimani.app.shared.model.MenuFolder;

public class GetMenuActionResult extends BaseERPActionResult {

	MenuFolder menus;
	
	public GetMenuActionResult() {
		
	}

	public MenuFolder getMenus() {
		return menus;
	}

	public void setMenus(MenuFolder menus) {
		this.menus = menus;
	}


	/**
	 * Dummy data to enable Serialization
	 */
	Date today;
	
	BigDecimal num;
	
	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}
}
