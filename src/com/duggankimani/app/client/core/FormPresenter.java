package com.duggankimani.app.client.core;

import java.io.Serializable;
import java.util.ArrayList;

import com.duggankimani.app.client.components.ButtonPresenter;
import com.duggankimani.app.client.components.CheckboxPresenter;
import com.duggankimani.app.client.components.ComboPresenter;
import com.duggankimani.app.client.components.DateFieldPresenter;
import com.duggankimani.app.client.components.NumberFieldPresenter;
import com.duggankimani.app.client.components.SearchPresenter;
import com.duggankimani.app.client.components.TextFieldPresenter;
import com.duggankimani.app.client.events.AfterFormLoadEvent;
import com.duggankimani.app.client.events.CalloutEvent;
import com.duggankimani.app.client.events.CalloutEvent.CalloutHandler;
import com.duggankimani.app.client.events.ClearFieldsEvent;
import com.duggankimani.app.client.events.ClearLinesEvent;
import com.duggankimani.app.client.events.CreateEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.LoadLineDataEvent;
import com.duggankimani.app.client.events.LoadTabsEvent;
import com.duggankimani.app.client.events.NavigateEvent;
import com.duggankimani.app.client.events.UndoEvent;
import com.duggankimani.app.client.events.CreateEvent.CreateHandler;
import com.duggankimani.app.client.events.NavigateEvent.NavigateHandler;
import com.duggankimani.app.client.events.SetValueEvent;
import com.duggankimani.app.client.events.SetValueEvent.SetValueHandler;
import com.duggankimani.app.client.events.UndoEvent.UndoHandler;
import com.duggankimani.app.client.events.ValueChangedEvent;
import com.duggankimani.app.client.events.ValueChangedEvent.ValueChangedHandler;
import com.duggankimani.app.client.menu.InputFormMenuPresenter;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.CreateRecordAction;
import com.duggankimani.app.shared.action.CreateRecordActionResult;
import com.duggankimani.app.shared.action.ExecCallout;
import com.duggankimani.app.shared.action.ExecCalloutResult;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.action.GetDataActionResult;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.duggankimani.app.shared.action.GetWindowActionResult;
import com.duggankimani.app.shared.action.UndoAction;
import com.duggankimani.app.shared.action.UndoActionResult;
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

/**
 * Form Presenter Widget
 * @author duggan
 *
 */
public class FormPresenter extends PresenterWidget<FormPresenter.MyView> implements NavigateHandler, 
CreateHandler, SetValueHandler, UndoHandler, ValueChangedHandler, CalloutHandler{

	public interface MyView extends View {
		void navigateNextRow();

		void setColSpan(int colSpan);
		
		void setTitle(String title);
		
		void clearLinesComponent();
		
		void setViewMode(int mode);

		void clear();
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
	
	private IndirectProvider<SearchPresenter> searchFactory;

	@Inject	InputFormMenuPresenter menu;

	private WindowModel windowModel;
	
	private TabModel curTab;

	@Inject TabsPresenter tabsPresenter;
	
	private DataModel savedData;//data loaded from the db
	
	//updates && new data held here
	private DataModel newData;

	enum MODE{
		EDIT, VIEW, UPDATE;
	}
	
	MODE Mode = MODE.VIEW; 
	
	@Inject
	public FormPresenter(final EventBus eventBus, final MyView view,
			Provider<TextFieldPresenter> textPageProvider,
			Provider<DateFieldPresenter> datePageProvider,
			Provider<NumberFieldPresenter> numberFieldProvider,
			Provider<CheckboxPresenter> checkboxProvider,
			Provider<ComboPresenter> comboProvider,
			Provider<ButtonPresenter> buttonProvider,
			Provider<SearchPresenter> searchProvider) {
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
		
		this.searchFactory = new StandardProvider<SearchPresenter>(searchProvider);

	}

	@Override
	protected void onBind() {
		super.onBind();
		
		addRegisteredHandler(NavigateEvent.TYPE, this);
		addRegisteredHandler(CreateEvent.TYPE, this);
		addRegisteredHandler(SetValueEvent.TYPE, this);
		addRegisteredHandler(UndoEvent.TYPE, this);
		addRegisteredHandler(ValueChangedEvent.TYPE, this);
		addRegisteredHandler(CalloutEvent.TYPE, this);
	}
		
	void loadWindow(GetWindowAction action){
		
		fireEvent(new ERPRequestProcessingEvent(""+action.getAD_Menu_ID()));
		dispatchAsync.execute(action,
				new ERPAsyncCallback<GetWindowActionResult>() {

					@Override
					public void processResult(GetWindowActionResult result) {
						getView().clear();
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
		case TABLE:
		case TABLEDIR:
		case LOCATION:
			createComboField(field);
			break;
		case SEARCH:
			createSearchField(field);
			break;
		default:
			createTextField(field);
		}

	}

	private void createSearchField(final FieldModel field) {
		searchFactory.get(new ERPAsyncCallback<SearchPresenter>() {
			@Override
			public void processResult(SearchPresenter result) {
				result.setFieldModel(field);
				FormPresenter.this.addToSlot(COMPONENT_SLOT, result);				
			}
		});
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

		GetDataAction action  = new GetDataAction(curTab.getTabNo(), curTab.getWindowID(), curTab.getWindowID(), 0);
		if(requestedAction!=null){
			action.setRowNo(requestedAction.getRowNo());
		}
		
		loadData(action);

	}
	

	/**
	 * Loads this components data
	 */
	protected void loadData(GetDataAction action) {
		loadData(action, false);
	}
	
	protected void loadData(GetDataAction action, final boolean reloadLinesToo) {
		fireEvent(new ERPRequestProcessingEvent("loading....."));
		dispatchAsync.execute(action,
				new ERPAsyncCallback<GetDataActionResult>() {
					@Override
					public void processResult(GetDataActionResult result) {

						if (result != null && !result.getDataModel().isEmpty()) {
							DataModel dataModel = result.getDataModel().get(0);
							if (dataModel != null){
								fireEvent(new SetValueEvent(dataModel,curTab.getWindowID(), curTab.getTabNo()));
								FormPresenter.this.savedData=dataModel;
							
								if(reloadLinesToo){
									fireEvent(new LoadTabsEvent(curTab.getWindowID(), curTab.getTabNo(), curTab.getTabLevel()));
								}
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
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();	
		setInSlot(MENU_SLOT, menu);
		loadWindow(requestedAction);	
	}

	@Override
	public void onNavigate(NavigateEvent event) {
		if(event.getSource()!=menu)
			return;
		
		loadData(new GetDataAction(curTab.getTabNo(), 0, curTab.getWindowID(), 0,false, event.getRows(), -1), true);
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

	@Override
	public void onCreate(CreateEvent event) {
		if(event.getSource()==menu){
			
			fireEvent(new ClearFieldsEvent(curTab.getWindowID(), curTab.getTabNo()));
			fireEvent(new ClearLinesEvent(curTab.getWindowID(), curTab.getTabNo(), curTab.getTabLevel()));
			Mode = MODE.EDIT;
			createNew();
		}
	}

	private void createNew() {
		int currentRow = savedData==null? 0 : savedData.getRowNo();
		fireEvent(new ERPRequestProcessingEvent("processing........"));
		dispatchAsync.execute(new CreateRecordAction(curTab.getWindowID(), 0, curTab.getTabNo(), currentRow), 
				new ERPAsyncCallback<CreateRecordActionResult>() {
			@Override
			public void processResult(CreateRecordActionResult result) {
				fireEvent(new ERPRequestProcessingCompletedEvent());
				fireEvent(new SetValueEvent(result.getData(),curTab.getWindowID(), curTab.getTabNo()));
			}
		});
	}

	@Override
	public void onSetValue(SetValueEvent event) {
		if(event.getSource()==this){
			if(Mode==MODE.VIEW){
				savedData = event.getData();
				
			}else{
				//edits and new info here
				newData = event.getData();
			}
		}
	}

	@Override
	public void onUndo(UndoEvent event) {
		if(event.getSource()!=menu)
			return;
		Mode = MODE.VIEW;
		int currentRow = savedData==null? 0 : savedData.getRowNo();
		
		fireEvent(new ERPRequestProcessingEvent("processing....."));
		dispatchAsync.execute(new UndoAction(curTab.getWindowID(), curTab.getTabNo(), currentRow), 
				new ERPAsyncCallback<UndoActionResult>() {
			@Override
			public void processResult(UndoActionResult result) {
				fireEvent(new ERPRequestProcessingCompletedEvent());
				if(result.getData()!=null){
					fireEvent(new SetValueEvent(result.getData(),curTab.getWindowID(), curTab.getTabNo()));
					fireEvent(new LoadTabsEvent(curTab.getWindowID(), curTab.getTabNo(), curTab.getTabLevel()));
				}
				else{
					fireEvent(new ClearFieldsEvent(curTab.getWindowID(), curTab.getTabNo()));
				}
			}
		});
	}

	@Override
	public void onValueChanged(ValueChangedEvent event) {
		
		if (!(event.getWindowId().equals(curTab.getWindowID()) &&
				event.getTabNo().equals(curTab.getTabNo()))){
			return;
		}
		
		fireEvent(new ERPRequestProcessingEvent("setting value...."));
		
		Object value = event.getNewValue();
		String columnName =event.getColumnName(); 
	
		if(newData==null){
			newData = new DataModel();
		}
		
		if(value!=null){
			newData.set(columnName, (Serializable)value);
		}
		fireEvent(new ERPRequestProcessingCompletedEvent());
	}

	@Override
	public void onCallout(CalloutEvent event) {
		if (!(event.getWindowId().equals(curTab.getWindowID()) &&
				event.getTabNo().equals(curTab.getTabNo()))){
			return;
		}
		
		fireEvent(new ERPRequestProcessingEvent("computing...."));
		dispatchAsync.execute(new ExecCallout(curTab.getWindowID(), curTab.getTabNo(), event.getColumnName(), newData), 
				new ERPAsyncCallback<ExecCalloutResult>() {
			@Override
			public void processResult(ExecCalloutResult result) {
				fireEvent(new ERPRequestProcessingCompletedEvent());
//				System.out.println("New Values - "+result.getData().getData().keySet().toString());
//				System.out.println("New Values - "+result.getData().getData().values().toString());
				fireEvent(new SetValueEvent(result.getData(),curTab.getWindowID(), curTab.getTabNo()));
			}
		});
	}

}
