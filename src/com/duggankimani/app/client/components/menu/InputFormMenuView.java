package com.duggankimani.app.client.components.menu;

import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class InputFormMenuView extends ViewImpl implements InputFormMenuPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, InputFormMenuView> {
	}

	@UiField Menu actionMenu;
	@UiField TextButton btnActions;
	@UiField TextButton prev;
	@UiField TextButton next;
	@UiField TextButton btnNew;
	@UiField TextButton btnCopy;
	@UiField TextButton btnSave;
	@UiField TextButton btnUndo;
	
	boolean hasPrevious=false;
	boolean hasNext=false;
	
	int mode=0;
	
	@Inject
	public InputFormMenuView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void addField(FieldModel field) {
		if(!btnActions.isEnabled())
			btnActions.enable();
		
		MenuItem item = new MenuItem(field.getName());
		actionMenu.add(item);		
	}

	@Override
	public void clearActions() {
		
		actionMenu.clear();
		btnActions.disable();
	}

	public TextButton getNext() {
		return next;
	}

	public TextButton getPrev() {
		return prev;
	}

	public TextButton getNew(){
		return btnNew;
	}
	
	public TextButton getCopy(){
		return btnCopy;
	}
	
	public TextButton getUndo(){
		return btnUndo;
	}
	
	@Override
	public void setNavigationState(Boolean hasPrev, Boolean hasNext) {
		
		if(mode!=1){ //1= editing
			prev.setEnabled(hasPrev);
			next.setEnabled(hasNext);
			this.hasNext=hasNext;
			this.hasPrevious=hasPrev;
		}
	}

	
	@Override
	public void setMode(int mode) {
		this.mode=mode;
		actionMenu.setEnabled(false);
		btnActions.setEnabled(false);
		prev.setEnabled(false);
		next.setEnabled(false);
		btnNew.setEnabled(false);
		btnCopy.setEnabled(false);
		btnSave.setEnabled(false);
		btnUndo.setEnabled(false);
		
		if(mode==0){
			//normal view mode
			actionMenu.setEnabled(true);
			btnActions.setEnabled(true);
			prev.setEnabled(hasPrevious);
			next.setEnabled(hasNext);
			btnNew.setEnabled(true);
			btnCopy.setEnabled(true);
		}else if(mode==1){
			//creating or editing document
			//disable all except undo
			btnSave.setEnabled(true);
			btnUndo.setEnabled(true);
		}		
	}
}
