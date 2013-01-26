package com.duggankimani.app.client.core;

import java.util.List;

import com.duggankimani.app.shared.model.MinTabModel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;

public class InputLinesTabsView extends ViewImpl implements
		InputLinesTabsPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, InputLinesTabsView> {
	}

	@UiField PlainTabPanel tabContainer = new PlainTabPanel();
	
	@Inject
	public InputLinesTabsView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		tabContainer.setTabScroll(true);
	}

	public void bind(List<MinTabModel> tabs) {
		for (MinTabModel tab : tabs) {
			tabContainer.add(new VerticalPanel(), new TabItemConfig(tab.getName()));
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

			for(int i=tabContainer.getWidgetCount()-1; i>=0; i--){
				tabContainer.remove(i);
			}

			if (content != null) {
				//reset mechanism
			}
		} else{
			super.setInSlot(slot, content);
		}
	}
	
	@Override
	public void addToSlot(Object slot, Widget content) {

		if(slot==InputLinesTabsPresenter.TAB_SLOT){
			int tab = tabContainer.getTabIndex();
			VerticalPanel panel = (VerticalPanel) tabContainer.getWidget(tab);

			if(content!=null){
				panel.add(content);
			}
		}else{
			super.addToSlot(slot, content);
		}
	}
}
