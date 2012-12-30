package com.duggankimani.app.client.workbench;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class FinanceWorkbenchView extends ViewImpl implements
		FinanceWorkbenchPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, FinanceWorkbenchView> {
	}

	@Inject
	public FinanceWorkbenchView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
