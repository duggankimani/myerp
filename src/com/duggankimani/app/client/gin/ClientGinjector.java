package com.duggankimani.app.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.duggankimani.app.client.gin.ClientModule;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.inject.client.AsyncProvider;
import com.duggankimani.app.client.core.MainPagePresenter;
import com.duggankimani.app.client.core.InputFormPresenter;
import com.duggankimani.app.client.workbench.FinanceWorkbenchPresenter;
import com.duggankimani.app.client.components.menu.MenuPresenter;
import com.duggankimani.app.client.core.ErrorPagePresenter;
import com.duggankimani.app.client.core.InputLinesPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AsyncProvider<MainPagePresenter> getMainPagePresenter();

	AsyncProvider<InputFormPresenter> getHomePagePresenter();

	AsyncProvider<FinanceWorkbenchPresenter> getFinanceWorkbenchPresenter();

	AsyncProvider<MenuPresenter> getMenuPresenter();

	AsyncProvider<ErrorPagePresenter> getErrorPagePresenter();

	AsyncProvider<InputLinesPresenter> getInputLinesPresenter();
}
