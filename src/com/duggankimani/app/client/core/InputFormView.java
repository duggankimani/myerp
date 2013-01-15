package com.duggankimani.app.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class InputFormView extends ViewImpl implements InputFormPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, InputFormView> {
	}

	@UiField FlexTable flexTable;
	
	@UiField HorizontalPanel menuContainer;
	
	@UiField HTMLPanel linesContainer;
	
	@UiField SpanElement spnTitle;
	
	@Inject
	public InputFormView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	int x=0;
	int y=-1;
	
	int row=0;
	int col=0;
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		x=0; y=0;
		
		if (slot == InputFormPresenter.COMPONENT_SLOT) {

			flexTable.clear();
			if (content != null) {
				row=y;
				col=x;
				flexTable.setWidget(y, x++, content);
			}
		}else if(slot == InputFormPresenter.MENU_SLOT){
			menuContainer.clear();
			if(content!=null)
				menuContainer.add(content);
		}else if(slot == InputFormPresenter.LINES_SLOT){
			linesContainer.clear();
			if(content!=null){
				linesContainer.add(content);
			}
		}
		else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public void addToSlot(Object slot, Widget content) {
		if (slot == InputFormPresenter.COMPONENT_SLOT) {
			if (content != null) {
				
				row=y;
				col=x++;
				flexTable.setWidget(row, col , content);
				
			}
		} else {
			super.addToSlot(slot, content);
		}
	}

	@Override
	public void navigateNextRow() {
		x=0;
		++y;
	}

	public HTMLPanel getLinesContainer() {
		return linesContainer;
	}

	@Override
	public void setColSpan(int colSpan) {
		x=colSpan+x;
		flexTable.getFlexCellFormatter().setColSpan(row, col, colSpan);
	}
	
	public void setTitle(String title){
		spnTitle.setInnerText(title);
	}

}
