package com.duggankimani.app.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.server.handlers.GetWindowActionActionHandler;
import com.duggankimani.app.shared.action.GetMenuAction;
import com.duggankimani.app.server.handlers.GetMenuActionActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(GetWindowAction.class, GetWindowActionActionHandler.class);

		bindHandler(GetMenuAction.class, GetMenuActionActionHandler.class);
	}
}
