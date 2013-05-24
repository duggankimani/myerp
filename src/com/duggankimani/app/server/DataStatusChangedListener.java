package com.duggankimani.app.server;

import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridTab;

/**
 * DataStatusListener
 * 
 * @author duggan
 *
 */
public class DataStatusChangedListener implements DataStatusListener{

	static ThreadLocal<DataStatusChangedListener> listener = new ThreadLocal<>();

	private DataStatusEvent event;	
	
	private GridTab curTab;
	
	private DataStatusChangedListener(){
	}
	
	public static DataStatusChangedListener get(){
		DataStatusChangedListener dataStatusListener = listener.get();
		
		Object sync = new Object();
		
		if(dataStatusListener==null)
			synchronized (sync) {
				dataStatusListener = new DataStatusChangedListener();
				listener.set(dataStatusListener);
			}
		return listener.get();
	}
	
	public static void clear(){
		listener.set(null);
	}
	
	/**
	 * issue; if more than 1 event is fired, only the last event will
	 * be shipped to the front end
	 */
	@Override
	public void dataStatusChanged(DataStatusEvent event) {
		this.event = event;
	}

	public DataStatusEvent getEvent() {
		return event;
	}

	public void setEvent(DataStatusEvent event) {
		this.event = event;
	}

	public GridTab getCurTab() {
		return curTab;
	}

	public void setCurTab(GridTab curTab) {
		this.curTab = curTab;
	}

}
