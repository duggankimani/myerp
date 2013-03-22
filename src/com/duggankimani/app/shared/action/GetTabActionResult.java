package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.TabModel;

public class GetTabActionResult extends BaseActionResult {

	private TabModel tabModel;

	@SuppressWarnings("unused")
	private GetTabActionResult() {
	}

	public GetTabActionResult(TabModel tabModel) {
		this.tabModel = tabModel;
	}

	public TabModel getTabModel() {
		return tabModel;
	}
}
