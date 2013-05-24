package com.duggankimani.app.shared.action;

public class GetMenuAction extends BaseAction<GetMenuActionResult> {

	public GetMenuAction() {
	}
	
	@Override
	public BaseActionResult createDefaultActionResponse() {
	
		return new GetMenuActionResult();
	}
	
}
