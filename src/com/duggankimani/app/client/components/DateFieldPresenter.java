package com.duggankimani.app.client.components;

import java.util.Date;

import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class DateFieldPresenter extends
		BasePresenterWidget<DateFieldPresenter.MyView> {

	public interface MyView extends BaseView {
		public void setValue(Date value);
	}

	@Inject
	public DateFieldPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void setValue(Object value) {
			((MyView)getView()).setValue((Date)value);	
	}

}
