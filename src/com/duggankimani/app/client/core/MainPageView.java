package com.duggankimani.app.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainPageView extends ViewImpl implements MainPagePresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, MainPageView> {
	}

	
	@UiField
	HTMLPanel container;

	//@UiField 
	Anchor anchorHome =  new Anchor();
	
	@UiField
	Anchor createNew;
	
	@UiField
	HTMLPanel menuContainer;
	
	@UiField
	SpanElement loadingComponent;
	
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
		
		if (slot == MainPagePresenter.SLOT_content) {
			container.clear();
			
			if (content != null) {
				container.add(content);
			}
		} else if(slot == MainPagePresenter.MENU_SLOT){
			menuContainer.clear();
			
			if(content!=null){
				menuContainer.add(content);
			}
		}else
			super.setInSlot(slot, content);
	}

	public Anchor getAnchorHome() {
		return anchorHome;
	}

	public Anchor getCreateNew() {
		return createNew;
	}
	
	public void showLoadingMessage(String message){
		if(message==null)
			message = "";
		
		loadingComponent.setInnerText("loading "+message+"....");
	}
	
	public void hideLoadingMessage(){
		loadingComponent.setInnerText("");
	}

}
