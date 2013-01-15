package com.duggankimani.app.client.core;

import java.util.List;

import com.duggankimani.app.shared.model.MinTabModel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class InputLinesTabsView extends ViewImpl implements
		InputLinesTabsPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, InputLinesTabsView> {
	}

	@UiField
	TabPanel tabContainer = new TabPanel();

	@Inject
	public InputLinesTabsView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	public void bind(List<MinTabModel> tabs) {
		tabContainer.clear();
		for (MinTabModel tab : tabs) {
			tabContainer.add(new VerticalPanel(), tab.getName());
		}

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public TabPanel getContainer() {
		return tabContainer;
	}

	public TabPanel getTabPanel() {
		return tabContainer;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == InputLinesTabsPresenter.TAB_SLOT) {

			int tab = tabContainer.getTabBar().getSelectedTab();

			if(tab==-1)
				return;
			
			VerticalPanel panel = (VerticalPanel) tabContainer.getWidget(tab);

			panel.clear();

			if (content != null) {
				panel.add(content);
			}
		} else
			super.setInSlot(slot, content);
	}
}
