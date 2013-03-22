package com.duggankimani.app.client.components;

import com.google.inject.Inject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.form.CheckBox;

public class CheckboxPresenter extends BasePresenterWidget<CheckboxPresenter.MyView> {

	public interface MyView extends BaseView {
		void setValue(Boolean value);
		
		CheckBox getComponent();
	}

	@Inject
	public CheckboxPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);

	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getComponent().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				valueChanged(event.getValue());
			}
		});
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Boolean)
		getView().setValue((Boolean)value);
		else
			throw new RuntimeException("Unsupported Checkbox Type; FieldName="+fieldModel.getName()+", value = "+value);
	}

}
