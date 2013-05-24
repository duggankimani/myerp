package com.duggankimani.app.client.menu;

import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.duggankimani.app.client.core.process.ProcessDialogPresenter;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetMenuAction;
import com.duggankimani.app.shared.action.GetMenuActionResult;
import com.duggankimani.app.shared.model.MenuFolder;

public class ApplicationMenuPresenter extends
		PresenterWidget<ApplicationMenuPresenter.MyView> {

	public interface MyView extends View {
		void processFolder(MenuFolder folder);

		void setProcessHandler(SelectionHandler<Item> reportHandler);
	}
	
	@Inject
	DispatchAsync dispatcher;
	
	private IndirectProvider<ProcessDialogPresenter> processDialogProvider;
	
	@Inject
	public ApplicationMenuPresenter(final EventBus eventBus, final MyView view,
			Provider<ProcessDialogPresenter> processProvider) {
		super(eventBus, view);
		
		processDialogProvider = new StandardProvider<ProcessDialogPresenter>(processProvider); 
		
	}

	SelectionHandler<Item> reportHandler = new SelectionHandler<Item>() {
		@Override
		public void onSelection(SelectionEvent<Item> event) {
			//showProcessWindow(new Integer(event.getSelectedItem().getId()));
			Item item = event.getSelectedItem();
			//fireEvent(new LoadProcessEvent(new Integer(item.getId()), 0, 0, 0));
			
			showProcessWindow(new Integer(item.getId()));
		}
	};
	
	private void init() {
		dispatcher.execute(new GetMenuAction(),
				new ERPAsyncCallback<GetMenuActionResult>() {
					@Override
					public void processResult(GetMenuActionResult result) {
						ApplicationMenuPresenter.this.getView().processFolder(
								result.getMenus());
					}
				});

	}

	protected void showProcessWindow(final Integer menuId) {
		processDialogProvider.get(new ERPAsyncCallback<ProcessDialogPresenter>() {

			@Override
			public void processResult(ProcessDialogPresenter result) {
				result.setMenuId(menuId);
				addToPopupSlot(result, true);
			}
			
		});
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setProcessHandler(reportHandler);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		init();
	}

	@Override
	protected void onReset() {
		super.onReset();
	}
}
