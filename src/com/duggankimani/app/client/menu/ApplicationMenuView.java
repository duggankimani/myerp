package com.duggankimani.app.client.menu;

import com.duggankimani.app.client.events.MenuSelectEventHandler;
import com.duggankimani.app.client.resources.ERPIMAGES;
import com.duggankimani.app.shared.model.MenuFolder;
import com.duggankimani.app.shared.model.MenuType;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class ApplicationMenuView extends ViewImpl implements ApplicationMenuPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, ApplicationMenuView> {
	}

	@UiField
	Menu rootMenu;

	@Inject
	PlaceManager placeManager;

	SelectionHandler<Item> processHandler;
	
	@Inject
	public ApplicationMenuView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {

		return widget;
	}
	
	

	private void processFolder(Menu parentMenu, MenuFolder folder) {

		for (MenuFolder child : folder.getChildren()) {

			MenuItem menuItem = new MenuItem(child.getName());
			parentMenu.add(menuItem);

			if (!child.IsLeaf() && child.getChildren().size() != 0) {

				Menu menu = new Menu();
				menuItem.setSubMenu(menu);
				processFolder(menu, child);
			} else {
				menuItem.setId(child.getId() + "");
				if (child.getMenuType().equals(MenuType.WINDOW)) {
					// menuItem.setIcon(ERPIMAGES.INSTANCE.get_WindowIcon());
				} else if (child.getMenuType().equals(MenuType.PROCESS))
					menuItem.setIcon(ERPIMAGES.INSTANCE.get_ProcessIcon());
				else if (child.getMenuType().equals(MenuType.REPORT))
					menuItem.setIcon(ERPIMAGES.INSTANCE.get_ReportIcon());
				else
					menuItem.setIcon(ERPIMAGES.INSTANCE.get_ShovelIcon());

				if(child.getMenuType()==MenuType.WINDOW){
					
					menuItem.addSelectionHandler(new MenuSelectEventHandler(placeManager, child));					
				}
				
				if(child.getMenuType()==MenuType.REPORT || child.getMenuType()==MenuType.PROCESS){
					menuItem.addSelectionHandler(processHandler);
				}
			}
		}

	}
	
	
	
	public void processFolder(MenuFolder folder) {
		rootMenu.clear();
		processFolder(rootMenu, folder);
		
//		SafeHtmlBuilder builder = new SafeHtmlBuilder();
//		generateHTML(folder, builder);
//		
//		listContainer.setInnerHTML(builder.toSafeHtml().asString());
		
		
	}
	
	private void generateHTML(MenuFolder folder,SafeHtmlBuilder builder){
		
		for(MenuFolder child: folder.getChildren()){
			if(child.IsLeaf()){
				generateChild(child, builder);
				continue;
			}
			
			builder.appendHtmlConstant(
					
					"<li class=\"drawer\" style=\"position: static;\">"+
						"<h2 class=\"drawer-handle\">"+child.getName()+"</h2>"+
						"<ul class=\"alldownloads\" style=\"height: 463px; overflow: hidden; display:none;\">"
					);
			
			generateHTML(child, builder);	
		
			builder.appendHtmlConstant(
					"</ul>"+
					"</li>" 
					);
		}
	}

	private void generateChild(MenuFolder folder, SafeHtmlBuilder builder) {
		
		builder.appendHtmlConstant("<li id=\"sn-automator\">"+
							"<a href=\"/downloads/macosx/automator/\">"+folder.getId()+"</a>"+
						"</li>");
	}

	@Override
	public void setProcessHandler(SelectionHandler<Item> processHandler) {
		this.processHandler=processHandler;
	}

}
