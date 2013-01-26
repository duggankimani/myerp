package com.duggankimani.app.client.core;

import java.util.List;

import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.LoadLineDataEvent;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetTabAction;
import com.duggankimani.app.shared.action.GetTabActionResult;
import com.duggankimani.app.shared.model.MinTabModel;
import com.duggankimani.app.shared.model.TabModel;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.shared.FastMap;
import com.sencha.gxt.widget.core.client.TabPanel;

public class InputLinesTabsPresenter extends
		PresenterWidget<InputLinesTabsPresenter.MyView> {

	public interface MyView extends View {
		void bind(List<MinTabModel> tabs);
		TabPanel getTabPanel();
	}

	@ContentSlot
	public static final Object TAB_SLOT = new Object();
	//public static final Type<RevealContentHandler<?>> TAB_SLOT = new Type<RevealContentHandler<?>>();
	
	@Inject InputLinesPresenter lines;
	
	FastMap<TabModel> tabs = new FastMap<TabModel>();
	
	@Inject DispatchAsync dispatcher;
	
	private IndirectProvider<InputLinesPresenter> linesFactory;
	
	@Inject
	public InputLinesTabsPresenter(final EventBus eventBus, final MyView view, Provider<InputLinesPresenter> linesProvider) {
		super(eventBus, view);
		linesFactory = new StandardProvider<InputLinesPresenter>(linesProvider);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getTabPanel().addSelectionHandler(new SelectionHandler<Widget>() {
			
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				int selectedIndx = getView().getTabPanel().getWidgetIndex(event.getSelectedItem());
				getView().getTabPanel().setTabIndex(selectedIndx);
				//Info.display("Selected Tab", (selectedIndx+1)+"");
				setLines(selectedIndx+1);
				
			}
		});
	}	

	protected void setLines(Integer selectedItem) {
		
		if(!tabs.containsKey(selectedItem+""))
			loadMetaAndData(selectedItem);
		
	}

	private void loadMetaAndData(final Integer selectedItem) {
		getEventBus().fireEvent(new ERPRequestProcessingEvent("Tab meta"));
		dispatcher.execute(new GetTabAction(0, selectedItem), new ERPAsyncCallback<GetTabActionResult>() {
			@Override
			public void processResult(GetTabActionResult result) {
				tabs.put(selectedItem+"", result.getTabModel());
				getEventBus().fireEvent(new ERPRequestProcessingCompletedEvent());
				addLinesView(result.getTabModel());
				
			}
		});
	}

	private void addLinesView(final TabModel tab) {
		linesFactory.get(new ERPAsyncCallback<InputLinesPresenter>() {

			@Override
			public void processResult(InputLinesPresenter result) {
				result.bind(tab);
				addToSlot(TAB_SLOT, result);
				loadLineData(tab);
			}
		});

	}

	protected void loadLineData(TabModel tab) {
		getEventBus().fireEvent(new LoadLineDataEvent(tab.getTabNo(), 0, tab.getWindowID()));
	}

	public void bindTabs(List<MinTabModel> mintabs) {
		setInSlot(TAB_SLOT, null);		
		this.tabs.clear();
		getView().bind(mintabs);
	}
	
	@Override
	protected void onReset() {
		super.onReset();
	}
	
}
