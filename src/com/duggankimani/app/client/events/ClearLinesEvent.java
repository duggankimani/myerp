package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class ClearLinesEvent extends
		GwtEvent<ClearLinesEvent.ClearLinesHandler> {

	public static Type<ClearLinesHandler> TYPE = new Type<ClearLinesHandler>();
	private Integer parentTabNo;
	private Integer parentTabLevel;
	private Integer parentWindowId;

	public interface ClearLinesHandler extends EventHandler {
		void onClearLines(ClearLinesEvent event);
	}

	public ClearLinesEvent(Integer AD_Window_ID, Integer parentTabNo, Integer parentTabLevel) {
		this.parentWindowId = AD_Window_ID;
		this.parentTabNo = parentTabNo;
		this.parentTabLevel = parentTabLevel;
	}

	public Integer getParentTabNo() {
		return parentTabNo;
	}

	@Override
	protected void dispatch(ClearLinesHandler handler) {
		handler.onClearLines(this);
	}

	@Override
	public Type<ClearLinesHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ClearLinesHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer AD_Window_ID, Integer parentTabNo, Integer parentTabLevel) {
		source.fireEvent(new ClearLinesEvent(AD_Window_ID, parentTabNo, parentTabLevel));
	}

	public Integer getParentTabLevel() {
		return parentTabLevel;
	}

	public void setParentTabLevel(Integer parentTabLevel) {
		this.parentTabLevel = parentTabLevel;
	}

	public Integer getParentWindowId() {
		return parentWindowId;
	}

	public void setParentWindowId(Integer parentWindowId) {
		this.parentWindowId = parentWindowId;
	}
}
