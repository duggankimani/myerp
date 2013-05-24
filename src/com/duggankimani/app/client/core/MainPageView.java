package com.duggankimani.app.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import static com.duggankimani.app.client.core.MainPagePresenter.*;

public class MainPageView extends ViewImpl implements MainPagePresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, MainPageView> {
	}

	
	@UiField HTMLPanel container;

	//@UiField 
	Anchor anchorHome =  new Anchor();
	
	@UiField HTMLPanel menuContainer;
	
	@UiField SpanElement loadingComponent;
	
	@UiField HTMLPanel headerContainer;
	
	@UiField HTMLPanel actionsContainer;
	
	@Inject
	public MainPageView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		
		if (slot == SLOT_content) {
			container.clear();
			
			if (content != null) {
				container.add(content);
			}
		} else if(slot == MENU_SLOT){
			menuContainer.clear();
			
			if(content!=null){
				menuContainer.add(content);
			}
		}else if(slot == HEADER_content){
			headerContainer.clear();
			
			if(content!=null){
				headerContainer.add(content);
			}
		}else if(slot == ACTIONS_content){
			actionsContainer.clear();
			
			if(content!=null){
				actionsContainer.add(content);
			}
		}		
		else
			super.setInSlot(slot, content);
	}

	public Anchor getAnchorHome() {
		return anchorHome;
	}
	
	public void showLoadingMessage(String message){
		if(message==null)
			message = "";
		
		loadingComponent.setInnerText("loading ....");
	}
	
	public void hideLoadingMessage(){
		loadingComponent.setInnerText("");
	}

}
