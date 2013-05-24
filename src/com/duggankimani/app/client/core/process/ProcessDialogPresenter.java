package com.duggankimani.app.client.core.process;

import java.util.ArrayList;

import com.duggankimani.app.client.components.ButtonPresenter;
import com.duggankimani.app.client.components.CheckboxPresenter;
import com.duggankimani.app.client.components.ComboPresenter;
import com.duggankimani.app.client.components.DateFieldPresenter;
import com.duggankimani.app.client.components.NumberFieldPresenter;
import com.duggankimani.app.client.components.SearchPresenter;
import com.duggankimani.app.client.components.TextFieldPresenter;
import com.duggankimani.app.client.core.FieldManager;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetProcessAction;
import com.duggankimani.app.shared.action.GetProcessActionResult;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.ProcessModel;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.PopupView;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;

/**
 * 
 * @author duggan
 *
 */
public class ProcessDialogPresenter extends
		PresenterWidget<ProcessDialogPresenter.IProcessDialog> {

	public interface IProcessDialog extends PopupView {
		HasClickHandlers getExecuteButton();
		
		HasClickHandlers getCancelButton();

		void bindValues(String name);
	}

	int menuId;
	
	FieldManager fieldManager = null;
	
	public static final Object COMPONENT_SLOT = new Object();
	
	private IndirectProvider<TextFieldPresenter> textBoxFactory;

	private IndirectProvider<DateFieldPresenter> dateFieldFactory;

	private IndirectProvider<NumberFieldPresenter> numberFieldFactory;

	private IndirectProvider<CheckboxPresenter> checkboxFactory;

	private IndirectProvider<ComboPresenter> comboFactory;

	private IndirectProvider<ButtonPresenter> buttonFactory;
	
	private IndirectProvider<SearchPresenter> searchFactory;
	
	@Inject	DispatchAsync dispatcher;
	
	ProcessModel process=null;
	
	@Inject
	public ProcessDialogPresenter(final EventBus eventBus, final IProcessDialog view,
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

		fieldManager = new FieldManager(this, COMPONENT_SLOT, getEventBus(),
				textBoxFactory, dateFieldFactory, numberFieldFactory,
				checkboxFactory, comboFactory, buttonFactory, searchFactory);
	}

	/**
	 * 
	 */
	private void loadProcess() {
		
		dispatcher.execute(new GetProcessAction(menuId), new ERPAsyncCallback<GetProcessActionResult>() {
			@Override
			public void processResult(GetProcessActionResult result) {
				ProcessModel model = result.getProcessModel();
				
				bindProcess(model);
			}
		});
	}

	protected void bindProcess(ProcessModel process) {
		
		getView().bindValues(process.getName());
		
		ArrayList<FieldModel> fields = process.getFields();
		
		if(fields!=null)
			for(FieldModel field:fields){
				fieldManager.createField(field);
			}
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getExecuteButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ProcessDialogPresenter.this.getView().hide();
			}
		});
		

		getView().getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ProcessDialogPresenter.this.getView().hide();
			}
		});
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		
		loadProcess();
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	
}
