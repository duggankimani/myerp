package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.duggankimani.app.shared.model.TabModel;
import com.google.gwt.event.shared.HasHandlers;

public class LoadLineEvent extends GwtEvent<LoadLineEvent.LoadLineHandler> {

	public static Type<LoadLineHandler> TYPE = new Type<LoadLineHandler>();
	private Integer tabNo;
	private TabModel tab;

	public interface LoadLineHandler extends EventHandler {
		void onLoadLine(LoadLineEvent event);
	}

	public LoadLineEvent(Integer tabNo, TabModel tab) {
		this.tabNo = tabNo;
		this.tab = tab;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public TabModel getTab() {
		return tab;
	}

	@Override
	protected void dispatch(LoadLineHandler handler) {
		handler.onLoadLine(this);
	}

	@Override
	public Type<LoadLineHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<LoadLineHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer tabNo, TabModel tab) {
		source.fireEvent(new LoadLineEvent(tabNo, tab));
	}
}
