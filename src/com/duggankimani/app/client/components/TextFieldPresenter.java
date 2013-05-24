package com.duggankimani.app.client.components;

import com.google.inject.Inject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TextFieldPresenter extends
		BasePresenterWidget<TextFieldPresenter.MyView> {

	public interface MyView extends BaseView {

		void setValue(String value);
		
		TextField getComponent();
	}

	@Inject
	public TextFieldPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getComponent().addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				valueChanged(event.getValue());
			}
		});
	}

	@Override
	public void setValue(Object value) {
		//java.math.BigDecimal --
		if(value instanceof String)
			getView().setValue((String)value);
		
		if(value!=null){
			getView().setValue(value.toString());
		}
		
	}
}
