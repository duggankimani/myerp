package com.duggankimani.app.client.components;

import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class TextFieldPresenter extends
		BasePresenterWidget<TextFieldPresenter.MyView> {

	public interface MyView extends BaseView {

		void setValue(String value);
	}

	@Inject
	public TextFieldPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void setValue(Object value) {
		//java.math.BigDecimal --
		((MyView)getView()).setValue((String)value);
		
	}
}
