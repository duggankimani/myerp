package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.TabModel;

public class GetTabActionResult extends BaseActionResult {

	private TabModel tabModel;

	public GetTabActionResult() {
	}

	public GetTabActionResult(TabModel tabModel) {
		this.tabModel = tabModel;
	}

	public TabModel getTabModel() {
		return tabModel;
	}

	public void setTabModel(TabModel tabModel) {
		this.tabModel = tabModel;
	}
}
