package com.duggankimani.app.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ActionsView extends ViewImpl implements ActionsPresenter.IActionsView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, ActionsView> {
	}

	@UiField HTMLPanel actionsPanel;
	
	@Inject
	public ActionsView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	public void clearAll(){
		actionsPanel.clear();
	}
	
	public void addAction(String url, String text, String title){
		Anchor anchor = new Anchor();
		anchor.setHref(url);
		anchor.setText(text);
		anchor.setTitle(title);
		
		HTMLPanel panel = new HTMLPanel("");
		panel.add(anchor);
		actionsPanel.add(panel);
	}
}
