package com.duggankimani.app.client.workbench;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class FinanceWorkbenchView extends ViewImpl implements
		FinanceWorkbenchPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, FinanceWorkbenchView> {
	}

	@UiField HTMLPanel container;
	
	@Inject
	public FinanceWorkbenchView(final Binder binder) {
		widget = binder.createAndBindUi(this);

//		SearchView view = new SearchView((SearchView.Binder)GWT.create(SearchView.Binder.class));
//		Widget w = view.asWidget();
//		container.add(w);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
