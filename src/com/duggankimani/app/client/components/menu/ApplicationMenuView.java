package com.duggankimani.app.client.components.menu;

import com.duggankimani.app.client.events.MenuSelectEventHandler;
import com.duggankimani.app.client.resources.ERPIMAGES;
import com.duggankimani.app.shared.model.MenuFolder;
import com.duggankimani.app.shared.model.MenuType;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
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

				menuItem.addSelectionHandler(new MenuSelectEventHandler(placeManager));
			}
		}

	}

	public void processFolder(MenuFolder folder) {
		rootMenu.clear();
		processFolder(rootMenu, folder);
	}

}
