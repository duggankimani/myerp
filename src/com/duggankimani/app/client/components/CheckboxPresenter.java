package com.duggankimani.app.client.components;

import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class CheckboxPresenter extends BasePresenterWidget<CheckboxPresenter.MyView> {

	public interface MyView extends BaseView {
		void setValue(Boolean value);
	}

	@Inject
	public CheckboxPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Boolean)
		((MyView)getView()).setValue((Boolean)value);
		else
			throw new RuntimeException("Unsupported Checkbox Type; FieldName="+fieldModel.getName()+", value = "+value);
	}

}
