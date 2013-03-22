package com.duggankimani.app.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.server.handlers.GetWindowActionHandler;
import com.duggankimani.app.shared.action.GetMenuAction;
import com.duggankimani.app.server.handlers.GetMenuActionHandler;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.server.handlers.GetDataActionHandler;
import com.duggankimani.app.shared.action.GetTabAction;
import com.duggankimani.app.server.handlers.GetTabActionHandler;
import com.duggankimani.app.shared.action.CreateRecordAction;
import com.duggankimani.app.server.handlers.CreateRecordActionHandler;
import com.duggankimani.app.shared.action.UndoAction;
import com.duggankimani.app.server.handlers.UndoActionHandler;
import com.duggankimani.app.shared.action.ExecCallout;
import com.duggankimani.app.server.handlers.ExecCalloutActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(GetWindowAction.class, GetWindowActionHandler.class);

		bindHandler(GetMenuAction.class, GetMenuActionHandler.class);

		bindHandler(GetDataAction.class, GetDataActionHandler.class);


		bindHandler(GetTabAction.class, GetTabActionHandler.class);

		bindHandler(CreateRecordAction.class, CreateRecordActionHandler.class);

		bindHandler(UndoAction.class, UndoActionHandler.class);

		bindHandler(ExecCallout.class, ExecCalloutActionHandler.class);
	}
}
