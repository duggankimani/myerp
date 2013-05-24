package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Boolean;
import com.google.gwt.event.shared.HasHandlers;

public class NavigationUpdateEvent extends
		GwtEvent<NavigationUpdateEvent.NavigationUpdateHandler> {

	public static Type<NavigationUpdateHandler> TYPE = new Type<NavigationUpdateHandler>();
	private Boolean isfirstRow;
	private Boolean isLastRow;
	private int windowId;
	private int windowNo;
	private int tabNo;
	
	public interface NavigationUpdateHandler extends EventHandler {
		void onNavigationUpdate(NavigationUpdateEvent event);
	}

	public NavigationUpdateEvent(int windowId,int windowNo, int tabNo, Boolean isfirstRow, Boolean isLastRow) {
		this.windowId= windowId;
		this.windowNo=windowNo;
		this.tabNo = tabNo;
		this.isfirstRow = isfirstRow;
		this.isLastRow = isLastRow;
	}

	public Boolean isfirstRow() {
		return isfirstRow;
	}

	public Boolean isLastRow() {
		return isLastRow;
	}

	@Override
	protected void dispatch(NavigationUpdateHandler handler) {
		handler.onNavigationUpdate(this);
	}

	@Override
	public Type<NavigationUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<NavigationUpdateHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source,int windowId, int windowNo, int tabNo, Boolean isfirstRow,
			Boolean isLastRow) {
		source.fireEvent(new NavigationUpdateEvent(windowId, windowNo, tabNo, isfirstRow, isLastRow));
	}

	public int getWindowNo() {
		return windowNo;
	}

	public void setWindowNo(int windowNo) {
		this.windowNo = windowNo;
	}

	public int getTabNo() {
		return tabNo;
	}

	public void setTabNo(int tabNo) {
		this.tabNo = tabNo;
	}

	public int getWindowId() {
		return windowId;
	}
}
