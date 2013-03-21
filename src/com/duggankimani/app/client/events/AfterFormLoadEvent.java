package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.duggankimani.app.client.core.FormPresenter;
import com.google.gwt.event.shared.HasHandlers;

public class AfterFormLoadEvent extends
		GwtEvent<AfterFormLoadEvent.AfterFormLoadHandler> {

	public static Type<AfterFormLoadHandler> TYPE = new Type<AfterFormLoadHandler>();
	private FormPresenter source;

	public interface AfterFormLoadHandler extends EventHandler {
		void onAfterFormLoad(AfterFormLoadEvent event);
	}

	boolean isSuccess=true;
	
	public AfterFormLoadEvent(FormPresenter source, boolean isSuccess) {
		this.source = source;
		this.isSuccess=isSuccess;
	}	

	public FormPresenter getSource() {
		return source;
	}

	@Override
	protected void dispatch(AfterFormLoadHandler handler) {
		handler.onAfterFormLoad(this);
	}

	@Override
	public Type<AfterFormLoadHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<AfterFormLoadHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, FormPresenter src, boolean isSuccess) {
		source.fireEvent(new AfterFormLoadEvent(src,isSuccess));
	}



	public boolean isSuccess() {
		return isSuccess;
	}



	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
