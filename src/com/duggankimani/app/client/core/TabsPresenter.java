package com.duggankimani.app.client.core;

import java.util.List;

import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.LoadLineDataEvent;
import com.duggankimani.app.client.events.LoadTabsEvent;
import com.duggankimani.app.client.events.LoadTabsEvent.LoadTabsHandler;
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

public class TabsPresenter extends PresenterWidget<TabsPresenter.MyView> implements LoadTabsHandler{

	public interface MyView extends View {
		void bind(List<MinTabModel> tabs);

		TabPanel getTabPanel();
	}

	@ContentSlot
	public static final Object TAB_SLOT = new Object();

	FastMap<TabModel> tabs = new FastMap<TabModel>();
	List<MinTabModel> minTabDetails = null;

	@Inject
	DispatchAsync dispatcher;

	private IndirectProvider<LinesPresenter> linesFactory;

	@Inject
	public TabsPresenter(final EventBus eventBus, final MyView view,
			Provider<LinesPresenter> linesProvider) {
		super(eventBus, view);
		linesFactory = new StandardProvider<LinesPresenter>(linesProvider);
	}

	@Override
	protected void onBind() {
		super.onBind();

		addRegisteredHandler(LoadTabsEvent.TYPE, this);
		getView().getTabPanel().addSelectionHandler(
				new SelectionHandler<Widget>() {

					@Override
					public void onSelection(SelectionEvent<Widget> event) {
						int selectedIndx = getView().getTabPanel()
								.getWidgetIndex(event.getSelectedItem());
						getView().getTabPanel().setTabIndex(selectedIndx);
						setLines(selectedIndx);
					}
				});
	}

	protected void setLines(Integer selectedItem) {

		MinTabModel selected =  minTabDetails.get(selectedItem);
		int tabno = selected.getTabNo();
		if (tabs.containsKey(tabno + "")) {
			// already loaded
			return;
		}

		loadMetaAndData(selected.getWindowId(), tabno);

	}

	private void loadMetaAndData(final Integer windowId, final Integer tabNo) {
		getEventBus().fireEvent(new ERPRequestProcessingEvent("Tab meta"));
		dispatcher.execute(new GetTabAction(windowId, tabNo),
				new ERPAsyncCallback<GetTabActionResult>() {
					@Override
					public void processResult(GetTabActionResult result) {
						tabs.put(tabNo + "", result.getTabModel());
						getEventBus().fireEvent(
								new ERPRequestProcessingCompletedEvent());
						addLinesView(result.getTabModel());

					}
				});
	}

	private void addLinesView(final TabModel tab) {
		linesFactory.get(new ERPAsyncCallback<LinesPresenter>() {

			@Override
			public void processResult(LinesPresenter result) {
				result.bind(tab);
				addToSlot(TAB_SLOT, result);
				loadLineData(tab);
			}
		});

	}

	protected void loadLineData(TabModel tab) {
		getEventBus().fireEvent(
				new LoadLineDataEvent(tab.getTabNo(), 0, tab.getWindowID()));
	}

	public void bindTabs(List<MinTabModel> mintabs) {
		this.minTabDetails = mintabs;
		setInSlot(TAB_SLOT, null);
		this.tabs.clear();
		getView().bind(mintabs);
	}

	@Override
	protected void onReset() {
		super.onReset();
	}
	
	@Override
	public void onLoadTabs(LoadTabsEvent event) {
		Integer tabLevel = event.getParentTabLevel();
		Integer tabNo = event.getParentTabNo();
		Integer windowId= event.getWindowId();
		
		int tabIndex = getView().getTabPanel().getTabIndex();
//		System.err.println("Selected Index = "+tabIndex);
		if(tabIndex<0 || minTabDetails==null){
			return;
		}
		
		int mtabNo = minTabDetails.get(tabIndex).getTabNo();
//		System.err.println("TabNo = "+mtabNo);
		if(tabs.containsKey(mtabNo+"")){
			TabModel tab = tabs.get(mtabNo+"");
//			System.err.println("TabNo = "+mtabNo+" IS CONTAINED");
			if(tab.getWindowID().equals(windowId) && tab.getTabNo()>tabNo && tab.getTabLevel()==tabLevel+1){
//				System.err.println("TabNo = "+mtabNo+" Fire Event Called");
				loadLineData(tab);
			}
			
		}
	}

}
