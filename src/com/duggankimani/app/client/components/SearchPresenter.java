package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.google.inject.Inject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.event.BeforeQueryEvent;
import com.sencha.gxt.widget.core.client.event.BeforeQueryEvent.BeforeQueryHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class SearchPresenter extends BasePresenterWidget<SearchPresenter.MyView> {


	public interface MyView extends BaseView {
		void init(FieldModel field);

		void setValue(Integer value);

		void setValue(String value);
		
		ComboBox<LookupValue> getComponent();

		void setValue(LookupValue value);
	}

	@Inject
	public SearchPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getComponent().addBeforeQueryHandler(new BeforeQueryHandler<LookupValue>() {

			@Override
			public void onBeforeQuery(BeforeQueryEvent<LookupValue> event) {
				System.err.println("querying.....");
			}
		});		
		
		getView().getComponent().addValueChangeHandler(new ValueChangeHandler<LookupValue>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<LookupValue> event) {
				valueChanged(event.getValue());
			}
		});
	}

	@Override
	public void setFieldModel(FieldModel field) {
		getView().init(field);
		
		super.setFieldModel(field);
		
	}
	
	@Override
	public void setValue(Object value) {
		if(value instanceof Integer)
			getView().setValue((Integer)value);
		
		if(value instanceof String)
			getView().setValue((String)value);
		
		if(value instanceof LookupValue)
			getView().setValue((LookupValue)value);
	}
}
