package com.duggankimani.app.client.core;

import java.util.ArrayList;

import com.duggankimani.app.client.components.ButtonPresenter;
import com.duggankimani.app.client.components.CheckboxPresenter;
import com.duggankimani.app.client.components.ComboPresenter;
import com.duggankimani.app.client.components.DateFieldPresenter;
import com.duggankimani.app.client.components.NumberFieldPresenter;
import com.duggankimani.app.client.components.TextFieldPresenter;
import com.duggankimani.app.client.components.menu.InputFormMenuPresenter;
import com.duggankimani.app.client.events.AfterFormLoadEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.NavigateEvent;
import com.duggankimani.app.client.events.NavigateEvent.NavigateHandler;
import com.duggankimani.app.client.events.SetValueEvent;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.action.GetDataActionResult;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.shared.action.GetWindowActionResult;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.TabModel;
import com.duggankimani.app.shared.model.WindowModel;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;

public class FormPresenter extends PresenterWidget<FormPresenter.MyView> implements NavigateHandler{

	public interface MyView extends View {
		void navigateNextRow();

		void setColSpan(int colSpan);
		
		void setTitle(String title);
		
		void clearLinesComponent();
		
		void setViewMode(int mode);
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> MENU_SLOT = new Type<RevealContentHandler<?>>();

	public static final Object COMPONENT_SLOT = new Object();

	@ContentSlot
	public static final Type<RevealContentHandler<?>> LINES_SLOT = new Type<RevealContentHandler<?>>();

	@Inject
	DispatchAsync dispatchAsync;
	
	GetWindowAction requestedAction;

	private IndirectProvider<TextFieldPresenter> textBoxFactory;

	private IndirectProvider<DateFieldPresenter> dateFieldFactory;

	private IndirectProvider<NumberFieldPresenter> numberFieldFactory;

	private IndirectProvider<CheckboxPresenter> checkboxFactory;

	private IndirectProvider<ComboPresenter> comboFactory;

	private IndirectProvider<ButtonPresenter> buttonFactory;

	@Inject
	InputFormMenuPresenter menu;

	private WindowModel windowModel;
	
	private TabModel curTab;

	@Inject InputLinesTabsPresenter tabsPresenter;
	

	@Inject
	public FormPresenter(final EventBus eventBus, final MyView view,
			Provider<TextFieldPresenter> textPageProvider,
			Provider<DateFieldPresenter> datePageProvider,
			Provider<NumberFieldPresenter> numberFieldProvider,
			Provider<CheckboxPresenter> checkboxProvider,
			Provider<ComboPresenter> comboProvider,
			Provider<ButtonPresenter> buttonProvider) {
		super(eventBus, view);
		
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

	}

	@Override
	protected void onBind() {
		super.onBind();
		
		addRegisteredHandler(NavigateEvent.TYPE, this);
	}
		
	void loadWindow(GetWindowAction action){
		fireEvent(new ERPRequestProcessingEvent(""+action.getAD_Menu_ID()));
		dispatchAsync.execute(action,
				new ERPAsyncCallback<GetWindowActionResult>() {

					@Override
					public void processResult(GetWindowActionResult result) {
						windowModel = result.getWindowModel();
						curTab= windowModel.getTab(0);
						addFields(result);
						fireEvent(new ERPRequestProcessingCompletedEvent());	
						loadData();						
						fireEvent(new AfterFormLoadEvent(FormPresenter.this, true));					
					}
					
					@Override
					public void onFailure(Throwable caught) {
						fireEvent(new AfterFormLoadEvent(FormPresenter.this, false));
						super.onFailure(caught);
					}
				});

	}
	
	public void addFields(GetWindowActionResult result) {
		//clear whatever was there before
		setInSlot(LINES_SLOT, null);	
				
		((InputFormMenuPresenter.MyView) menu.getView()).clearActions();
		((MyView)getView()).setTitle(result.getWindowModel().getTab(0).getName());
		
		ArrayList<FieldModel> fields = result.getWindowModel().getTab(0)
				.getFields();

		for (final FieldModel field : fields) {
			if (!field.isSameLine())
				this.getView().navigateNextRow();

			createField(field);
		}

		if (windowModel != null && windowModel.getTabs().size() > 1){
			createTabView(windowModel);
		}else{
			//remove lines Div
			getView().clearLinesComponent();
		}
			

	}


	private void createTabView(WindowModel curTab) {
		
		tabsPresenter.bindTabs(curTab.getMinTabDetails());
		setInSlot(LINES_SLOT, tabsPresenter);
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
		case COSTPRICE:
		case ROWID:
		case PATTRIBUTESET:
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
				FormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	private void createComboField(final FieldModel field) {

		comboFactory.get(new ERPAsyncCallback<ComboPresenter>() {
			@Override
			public void processResult(ComboPresenter result) {
				result.setFieldModel(field);
				FormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	private void createCheckboxField(final FieldModel field) {
		checkboxFactory.get(new ERPAsyncCallback<CheckboxPresenter>() {
			@Override
			public void processResult(CheckboxPresenter result) {
				result.setFieldModel(field);
				FormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});
	}

	private void createNumberField(final FieldModel field) {

		numberFieldFactory.get(new ERPAsyncCallback<NumberFieldPresenter>() {
			@Override
			public void processResult(NumberFieldPresenter result) {
				result.setFieldModel(field);
				FormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	public void createTextField(final FieldModel field) {

		textBoxFactory.get(new ERPAsyncCallback<TextFieldPresenter>() {
			public void processResult(TextFieldPresenter result) {
				result.setFieldModel(field);
				FormPresenter.this.addToSlot(COMPONENT_SLOT, result);
				//setColSpan(result.getView().getColSpan());
			}
		});

	}

	public void createDateField(final FieldModel field) {
		dateFieldFactory.get(new ERPAsyncCallback<DateFieldPresenter>() {
			@Override
			public void processResult(DateFieldPresenter result) {
				result.setFieldModel(field);
				FormPresenter.this.addToSlot(COMPONENT_SLOT, result);
			}
		});

	}

	/**
	 * Loads this components data
	 */
	protected void loadData() {

		fireEvent(new ERPRequestProcessingEvent("Data"));
		loadData(new GetDataAction(curTab.getTabNo(), curTab.getWindowID(), curTab.getWindowID(), 0));

	}
	

	/**
	 * Loads this components data
	 */
	protected void loadData(GetDataAction action) {

		fireEvent(new ERPRequestProcessingEvent("Data"));
		dispatchAsync.execute(action,
				new ERPAsyncCallback<GetDataActionResult>() {
					@Override
					public void processResult(GetDataActionResult result) {

						if (result != null && !result.getDataModel().isEmpty()) {
							DataModel dataModel = result.getDataModel().get(0);
							if (dataModel != null){
								fireEvent(new SetValueEvent(dataModel));
							}
						}
						fireEvent(new ERPRequestProcessingCompletedEvent());
					}
				});

	}
	
	/**
	 * Sets colspan to the last added
	 */
	void setColSpan(int colSpan){
		if(colSpan>1)
		getView().setColSpan(2);
	}
	
	//int i=0;
	@Override
	protected void onReset() {
		super.onReset();
		System.err.println("FRM On Reset called!!");
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();	
		System.err.println("FRM On Reveal called!!");
		setInSlot(MENU_SLOT, menu);
		loadWindow(requestedAction);	
	}

	@Override
	public void onNavigate(NavigateEvent event) {
		if(event.getSource()!=menu)
			return;
		
		loadData(new GetDataAction(curTab.getTabNo(), 0, curTab.getWindowID(), 0, event.getRows()));
	}

	/**
	 * Requested Load Action
	 * -- Set by Popup Presenters and InputFormPresenters
	 * -- Entry points for parent components setting the window details to load
	 * -- 
	 * @param requestWindowAction
	 */
	public void setAction(GetWindowAction action) {
		this.requestedAction = action;
	}

	public void setViewMode(int i) {
		getView().setViewMode(i);
	}

}
