package com.duggankimani.app.client.workbench;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.duggankimani.app.client.tests.Stock;
import com.duggankimani.app.client.tests.StockProperties;
import com.duggankimani.app.client.tests.TestData;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.IconButton.IconConfig;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.tips.QuickTip;

public class FinanceWorkbenchView extends ViewImpl implements
		FinanceWorkbenchPresenter.MyView {

	private final Widget widget;

	interface CellTemplates extends XTemplates {

		@XTemplate("<span style='{styles}' qtitle='Change' qtip='{qtip}'>{value}</span>")
		SafeHtml template(SafeStyles styles, String qtip, String value);

	}

	Portlet p;
	enum ToolConfig {
		GEAR(ToolButton.GEAR), CLOSE(ToolButton.CLOSE);

		private IconConfig config;

		private ToolConfig(IconConfig config) {
			this.config = config;
		}
	}

	public interface Binder extends UiBinder<Widget, FinanceWorkbenchView> {
	}

	@Inject
	public FinanceWorkbenchView(final Binder binder) {
		widget = binder.createAndBindUi(this);

	}

	@Override
	public Widget asWidget() {
		/*portal.setColumnWidth(0, .40);
		portal.setColumnWidth(1, .30);
		portal.setColumnWidth(2, .30);*/
		portal.setColumnWidth(0, .52);
		portal.setColumnWidth(1, .46);
		return widget;
	}

	private static final StockProperties props = GWT
			.create(StockProperties.class);

	@UiField
	PortalLayoutContainer portal;

	@UiField(provided = true)
	String txt = TestData.DUMMY_TEXT_SHORT;

	@UiFactory()
	public Grid<Stock> createGrid() {
		final NumberFormat number = NumberFormat.getFormat("0.00");

		ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(
				props.name(), 200, "Company");
		ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(
				props.symbol(), 100, "Symbol");
		ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(
				props.last(), 75, "Last");

		ColumnConfig<Stock, Double> changeCol = new ColumnConfig<Stock, Double>(
				props.change(), 100, "Change");

		changeCol.setCell(new AbstractCell<Double>() {

			@Override
			public void render(Context context, Double value, SafeHtmlBuilder sb) {
				SafeStylesBuilder stylesBuilder = new SafeStylesBuilder();
				stylesBuilder.appendTrustedString("color:"
						+ (value < 0 ? "red" : "green") + ";");
				String v = number.format(value);
				CellTemplates cellTemplates = GWT.create(CellTemplates.class);
				SafeHtml template = cellTemplates.template(
						stylesBuilder.toSafeStyles(), v, v);
				sb.append(template);
			}
		});

		ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(
				props.lastTrans(), 100, "Last Updated");
		DateCell dateCell = new DateCell(DateTimeFormat
				.getFormat("MM/dd/yyyy"));
		lastTransCol.setCell(dateCell);

		List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
		l.add(nameCol);
		l.add(symbolCol);
		l.add(lastCol);
		l.add(changeCol);
		l.add(lastTransCol);
		ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

		ListStore<Stock> store = new ListStore<Stock>(props.key());
		store.addAll(TestData.getStocks());

		final Grid<Stock> grid = new Grid<Stock>(store, cm);
		grid.getView().setAutoExpandColumn(nameCol);
		grid.setBorders(false);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

		// needed to enable quicktips (qtitle for the heading and qtip for the
		// content) that are setup in the change GridCellRenderer
		new QuickTip(grid);

		return grid;
	}

	@UiFactory
	protected ToolButton createToolButton(ToolConfig icon, Portlet portlet) {
		ToolButton toolButton = new ToolButton(icon.config);
		toolButton.setData("portlet", portlet);
		return toolButton;
	}

	@UiHandler({ "portlet1Close", "portlet2Close", "portlet3Close",
			"portlet4Close" })
	protected void onClosePortlet(SelectEvent event) {
		ToolButton tool = (ToolButton) event.getSource();
		Portlet portlet = tool.getData("portlet");
		portlet.removeFromParent();
	}
}
