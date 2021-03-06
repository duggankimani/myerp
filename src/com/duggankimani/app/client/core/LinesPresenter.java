package com.duggankimani.app.client.core;

import java.util.ArrayList;

import com.duggankimani.app.client.events.ClearLinesEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.LoadLineDataEvent;
import com.duggankimani.app.client.events.ClearLinesEvent.ClearLinesHandler;
import com.duggankimani.app.client.events.LoadLineDataEvent.LoadLineDataHandler;
import com.duggankimani.app.client.events.LoadWindowEvent;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.action.GetDataActionResult;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.TabModel;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class LinesPresenter extends
		PresenterWidget<LinesPresenter.MyView> implements LoadLineDataHandler, ClearLinesHandler{

	public interface MyView extends View {
		public void bind(TabModel tabModel);

		void bindData(ArrayList<DataModel> dataModel);
		
		Grid<DataModel> getGrid();

		public void clearData();
	}

	@Inject DispatchAsync dispatcher;
	
	@Inject
	public LinesPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}


	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(LoadLineDataEvent.TYPE, this);
		addRegisteredHandler(ClearLinesEvent.TYPE, this);
		
	}

	TabModel tab = null;


	protected void setData(ArrayList<DataModel> dataModel) {
		getView().bindData(dataModel);		
	}

	private void loadData() {
		getEventBus().fireEvent(new ERPRequestProcessingEvent(tab.getName()));
		//check if data already loaded
		dispatcher.execute(new GetDataAction(tab.getTabNo(), 0, tab.getWindowID(), 0, true), new ERPAsyncCallback<GetDataActionResult>() {
			@Override
			public void processResult(GetDataActionResult result) {
				setData(result.getDataModel());
				//System.err.println("Results loaded -- "+tab.getName()+" :: "+result.getDataModel().get(0).getData().toString());
				getEventBus().fireEvent(new ERPRequestProcessingCompletedEvent());
			}
		});
	
	}
	
	@Override
	public void onLoadLineData(LoadLineDataEvent event) {
		if(tab==null)
			return;
		
		Integer tabNo = event.getTabNo();
		Integer windowId = event.getWindowID();
		event.getWindowNo();
		
		
		if(tab.getTabNo().equals(tabNo) && tab.getWindowID().equals(windowId)){
			//System.err.println("--Lines Presenter Loading me - "+tab.getTabNo()+" | "+tab.getWindowID());
			loadData();
		}
		
	}

	@Override
	protected void onReveal() {
		// TODO Auto-generated method stub
		super.onReveal();

		
		getView().getGrid().addRowDoubleClickHandler(new RowDoubleClickHandler() {
			
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
			
				DataModel model = getView().getGrid().getSelectionModel().getSelectedItem();
				LoadWindowEvent e = new LoadWindowEvent(new GetWindowAction(0, tab.getWindowID(), tab.getTabNo(), 0, model.getRowNo()));
				getEventBus().fireEvent(e); 
			}
		});
		
		
	}
	public void bind(TabModel tabModel) {
		
		this.tab = tabModel;
		getView().bind(tab);		
	}
	
	@Override
	protected void onHide() {
		super.onHide();
		this.unbind();
	}


	@Override
	public void onClearLines(ClearLinesEvent event) {
		int parentTabLevel = event.getParentTabLevel();
		int parentTabNo = event.getParentTabNo();
		int parentWindowId = event.getParentWindowId();
		if(tab==null)
			return;
	
		if(tab.getTabNo()>parentTabNo && tab.getTabLevel()>parentTabLevel && tab.getWindowID()==parentWindowId){
			//clear
			getView().clearData();
		}
	}

}
