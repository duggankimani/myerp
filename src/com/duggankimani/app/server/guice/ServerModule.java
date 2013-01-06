package com.duggankimani.app.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.server.handlers.GetWindowActionHandler;
import com.duggankimani.app.shared.action.GetMenuAction;
import com.duggankimani.app.server.handlers.GetMenuActionHandler;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.server.handlers.GetDataActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(GetWindowAction.class, GetWindowActionHandler.class);

		bindHandler(GetMenuAction.class, GetMenuActionHandler.class);

		bindHandler(GetDataAction.class, GetDataActionHandler.class);

	}
}
