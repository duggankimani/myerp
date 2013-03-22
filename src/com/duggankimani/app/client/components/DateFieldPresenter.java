package com.duggankimani.app.client.components;

import java.util.Date;

import com.google.inject.Inject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.form.DateField;

public class DateFieldPresenter extends
		BasePresenterWidget<DateFieldPresenter.MyView> {

	public interface MyView extends BaseView {
		public void setValue(Date value);
		
		DateField getComponent();
	}

	@Inject
	public DateFieldPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getComponent().addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				valueChanged(event.getValue());
			}
		});
	}

	@Override
	public void setValue(Object value) {
			getView().setValue((Date)value);	
	}

}
