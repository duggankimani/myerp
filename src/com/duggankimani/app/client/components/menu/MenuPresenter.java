package com.duggankimani.app.client.components.menu;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.duggankimani.app.client.core.MainPagePresenter;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetMenuAction;
import com.duggankimani.app.shared.action.GetMenuActionResult;
import com.duggankimani.app.shared.model.MenuFolder;

public class MenuPresenter extends
		Presenter<MenuPresenter.MyView, MenuPresenter.MyProxy> {

	public interface MyView extends View {
		void processFolder(MenuFolder folder);
	}

	@ProxyCodeSplit
	public interface MyProxy extends Proxy<MenuPresenter> {
	}

	@Inject
	DispatchAsync dispatcher;
	
	@Inject
	public MenuPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}
	
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.MENU_SLOT, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		// TODO Auto-generated method stub
		super.prepareFromRequest(request);
		
	}
	
	@Override
	protected void onReset() {
		// TODO Auto-generated method stub
		super.onReset();
		

		dispatcher.execute(new GetMenuAction(), new ERPAsyncCallback<GetMenuActionResult>() {
			@Override
			public void processResult(GetMenuActionResult result) {
				
				MenuPresenter.this.getView().processFolder(result.getMenus());
			}
		});
	
	}
}
