package com.duggankimani.app.client.components.menu;

import com.duggankimani.app.client.components.BaseView;
import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class InputFormMenuView extends ViewImpl implements
		BaseView,InputFormMenuPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, InputFormMenuView> {
	}

	@UiField
	Menu actionMenu;
	
	@UiField Button prev;
	@UiField Button next;
	@Inject
	public InputFormMenuView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public int getColSpan() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setColSpan(int colSpan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addField(FieldModel field) {
		MenuItem item = new MenuItem(field.getName());
		actionMenu.add(item);		
	}

	@Override
	public void clearActions() {
		
		actionMenu.clear();
		
	}

	@Override
	public HorizontalPanel getContainer() {
		return null;
	}

	@Override
	public void setDescription(String description) {
		
	}

	public Button getNext() {
		return next;
	}

	public void setNext(Button next) {
		this.next = next;
	}

	public Button getPrev() {
		return prev;
	}

	public void setPrev(Button prev) {
		this.prev = prev;
	}

}
