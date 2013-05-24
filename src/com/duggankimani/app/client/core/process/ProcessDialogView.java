package com.duggankimani.app.client.core.process;

import com.gwtplatform.mvp.client.PopupViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class ProcessDialogView extends PopupViewImpl implements
		ProcessDialogPresenter.IProcessDialog {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, ProcessDialogView> {
	}

	@UiField HTMLPanel parameterPanel; 
	
	@UiField FlexTable flexTable;
		
	@UiField Button btnExecute;
	
	@UiField Button btnCancel;
	
	@UiField SpanElement spnTitle;
	
	@UiField SpanElement spnDescription;
	
	
	@Inject
	public ProcessDialogView(final EventBus eventBus, final Binder binder) {
		super(eventBus);
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
		
		if (slot == ProcessDialogPresenter.COMPONENT_SLOT) {

			flexTable.clear();
			if (content != null) {
				row=y;
				col=x;
				flexTable.setWidget(y, x++, content);
			}
		}else {
			super.setInSlot(slot, content);
		}
		
	}
	
	@Override
	public void addToSlot(Object slot, Widget content) {

		if (slot == ProcessDialogPresenter.COMPONENT_SLOT) {
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
	public HasClickHandlers getExecuteButton() {
		
		return btnExecute;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return btnCancel;
	}

	@Override
	public void bindValues(String name) {
		
	}
}
