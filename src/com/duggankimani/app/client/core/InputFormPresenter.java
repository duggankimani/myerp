package com.duggankimani.app.client.core;

import java.util.ArrayList;

import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.duggankimani.app.client.place.NameTokens;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.duggankimani.app.client.components.ButtonPresenter;
import com.duggankimani.app.client.components.ComboPresenter;
import com.duggankimani.app.client.components.DateFieldPresenter;
import com.duggankimani.app.client.components.NumberFieldPresenter;
import com.duggankimani.app.client.components.TextFieldPresenter;
import com.duggankimani.app.client.components.CheckboxPresenter;
import com.duggankimani.app.client.components.menu.InputFormMenuPresenter;
import com.duggankimani.app.client.core.MainPagePresenter;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.SetValueEvent;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.action.GetDataActionResult;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.shared.action.GetWindowActionResult;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.TabModel;
import com.duggankimani.app.shared.model.WindowModel;

public class InputFormPresenter extends
		Presenter<InputFormPresenter.MyView, InputFormPresenter.MyProxy> {

	public interface MyView extends View {
		void navigateNextRow();

	}

	@ProxyCodeSplit
	@NameToken(NameTokens.inputfrm)
	public interface MyProxy extends ProxyPlace<InputFormPresenter> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> MENU_SLOT = new Type<RevealContentHandler<?>>();

	public static final Object COMPONENT_SLOT = new Object();

	@ContentSlot
	public static final Type<RevealContentHandler<?>> LINES_SLOT = new Type<RevealContentHandler<?>>();

	@Inject
	DispatchAsync dispatchAsync;

	private IndirectProvider<TextFieldPresenter> textBoxFactory;

	private IndirectProvider<DateFieldPresenter> dateFieldFactory;

	private IndirectProvider<NumberFieldPresenter> numberFieldFactory;

	private IndirectProvider<CheckboxPresenter> checkboxFactory;

	private IndirectProvider<ComboPresenter> comboFactory;

	private IndirectProvider<ButtonPresenter> buttonFactory;

	private IndirectProvider<InputLinesPresenter> linesFactory;

	@Inject
	InputFormMenuPresenter menu;

	private WindowModel windowModel;

	@Inject
	public InputFormPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, Provider<TextFieldPresenter> textPageProvider,
			Provider<DateFieldPresenter> datePageProvider,
			Provider<NumberFieldPresenter> numberFieldProvider,
			Provider<CheckboxPresenter> checkboxProvider,
			Provider<ComboPresenter> comboProvider,
			Provider<ButtonPresenter> buttonProvider,
			Provider<InputLinesPresenter> linesProvider) {

		super(eventBus, view, proxy);

		this.textBoxFactory = new StandardProvider<TextFieldPresenter>(
				textPageProvider);

		this.dateFieldFactory = new StandardProvider<DateFieldPresenter>(
				datePageProvider);

		this.numberFieldFactory = new StandardProvider<NumberFieldPresenter>(
				numberFieldProvider);

		this.checkboxFactory = new StandardProvider<CheckboxPresenter>(
				checkboxProvider);

		this.comboFactory = new StandardProvider<ComboPresenter>(comboProvider);

		this.buttonFactory = new StandardProvider<ButtonPresenter>(
				buttonProvider);

		linesFactory = new StandardProvider<InputLinesPresenter>(linesProvider);
		
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.SLOT_content, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public boolean useManualReveal() {
		return true;
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		this.setInSlot(COMPONENT_SLOT, null);
		this.setInSlot(LINES_SLOT, null);

		String AD_Menu_ID = request.getParameter("AD_Menu_ID", "0");

		GetWindowAction action = new GetWindowAction(new Integer(AD_Menu_ID));

		fireEvent(new ERPRequestProcessingEvent(AD_Menu_ID));

		dispatchAsync.execute(action,
				new ERPAsyncCallback<GetWindowActionResult>() {
					@Override
					public void onFailure(Throwable caught) {
						super.onFailure(caught);
						// must do this when using manual reveal
						fireEvent(new ERPRequestProcessingCompletedEvent());
						getProxy().manualRevealFailed();						
					}

					@Override
					public void processResult(GetWindowActionResult result) {
						addFields(result);
						getProxy().manualReveal(InputFormPresenter.this);
						fireEvent(new ERPRequestProcessingCompletedEvent());	
						loadData();						
											
					}
				});

	}

	public void addFields(GetWindowActionResult result) {

		this.windowModel = result.getWindowModel();

		((InputFormMenuPresenter.MyView) menu.getView()).clearActions();

		ArrayList<FieldModel> fields = result.getWindowModel().getTab(0)
				.getFields();

		for (final FieldModel field : fields) {
			if (!field.isSameLine())
				this.getView().navigateNextRow();

			createField(field);
		}

		if (windowModel != null && windowModel.getTabs().size() > 1)
			addLinesView(windowModel.getTab(1));

	}

	private void addLinesView(final TabModel tab) {

		linesFactory.get(new ERPAsyncCallback<InputLinesPresenter>() {

			@Override
			public void processResult(InputLinesPresenter result) {
				result.getView().bind(tab);
				setInSlot(LINES_SLOT, result);
			}
		});

	}

	public void createField(final FieldModel field) {

		switch (field.getDisplayType()) {
		case DATE:
		case DATETIME:
			createDateField(field);
			break;
		case NUMBER:
		case AMOUNT:
		case INTEGER:
		case ID:
		case QUANTITY:
		case ROWID:
			createNumberField(field);
			break;
		case YESNO:
			createCheckboxField(field);
			break;
		case BUTTON:
			createButtonField(field);
			break;
		case LIST:
		case LOCATOR:
		case SEARCH:
		case TABLE:
		case TABLEDIR:
		case LOCATION:
			createComboField(field);
			break;
		default:
			createTextField(field);
		}

	}

	private void createButtonField(final FieldModel field) {

		((InputFormMenuPresenter.MyView) menu.getView()).addField(field);

		buttonFactory.get(new ERPAsyncCallback<ButtonPresenter>() {
			@Override
			public void processResult(ButtonPresenter result) {
				result.setFieldModel(field);
				InputFormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	private void createComboField(final FieldModel field) {

		comboFactory.get(new ERPAsyncCallback<ComboPresenter>() {
			@Override
			public void processResult(ComboPresenter result) {
				result.setFieldModel(field);
				InputFormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	private void createCheckboxField(final FieldModel field) {
		checkboxFactory.get(new ERPAsyncCallback<CheckboxPresenter>() {
			@Override
			public void processResult(CheckboxPresenter result) {
				result.setFieldModel(field);
				InputFormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});
	}

	private void createNumberField(final FieldModel field) {

		numberFieldFactory.get(new ERPAsyncCallback<NumberFieldPresenter>() {
			@Override
			public void processResult(NumberFieldPresenter result) {
				result.setFieldModel(field);
				InputFormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	public void createTextField(final FieldModel field) {

		textBoxFactory.get(new ERPAsyncCallback<TextFieldPresenter>() {
			public void processResult(TextFieldPresenter result) {
				result.setFieldModel(field);
				InputFormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	public void createDateField(final FieldModel field) {
		dateFieldFactory.get(new ERPAsyncCallback<DateFieldPresenter>() {
			@Override
			public void processResult(DateFieldPresenter result) {
				result.setFieldModel(field);
				InputFormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	/**
	 * Loads this components data
	 */
	protected void loadData() {

		fireEvent(new ERPRequestProcessingEvent("Data"));
		dispatchAsync.execute(new GetDataAction(0, 0, 0, 0),
				new ERPAsyncCallback<GetDataActionResult>() {
					@Override
					public void processResult(GetDataActionResult result) {

						if (result != null) {
							DataModel dataModel = result.getDataModel();
							if (dataModel != null){
								fireEvent(new SetValueEvent(dataModel));
							}
						}
						fireEvent(new ERPRequestProcessingCompletedEvent());
					}
				});

	}

	@Override
	protected void onReset() {
		super.onReset();
		setInSlot(MENU_SLOT, menu);

	}

}
