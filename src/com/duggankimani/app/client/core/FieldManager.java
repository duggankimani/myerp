package com.duggankimani.app.client.core;

import com.duggankimani.app.client.components.ButtonPresenter;
import com.duggankimani.app.client.components.CheckboxPresenter;
import com.duggankimani.app.client.components.ComboPresenter;
import com.duggankimani.app.client.components.DateFieldPresenter;
import com.duggankimani.app.client.components.NumberFieldPresenter;
import com.duggankimani.app.client.components.SearchPresenter;
import com.duggankimani.app.client.components.TextFieldPresenter;
import com.duggankimani.app.client.events.ActionButtonCreatedEvent;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.mvp.client.HasSlots;

public class FieldManager{

	private HasSlots form;
	
	private Object SLOT;
	
	private IndirectProvider<TextFieldPresenter> textBoxFactory;

	private IndirectProvider<DateFieldPresenter> dateFieldFactory;

	private IndirectProvider<NumberFieldPresenter> numberFieldFactory;

	private IndirectProvider<CheckboxPresenter> checkboxFactory;

	private IndirectProvider<ComboPresenter> comboFactory;

	private IndirectProvider<ButtonPresenter> buttonFactory;
	
	private IndirectProvider<SearchPresenter> searchFactory;
	
	private EventBus eventBus;
	
	public FieldManager(HasSlots form, Object SLOT, EventBus eventBus,
	IndirectProvider<TextFieldPresenter> textBoxFactory,
	IndirectProvider<DateFieldPresenter> dateFieldFactory,
	IndirectProvider<NumberFieldPresenter> numberFieldFactory,
	IndirectProvider<CheckboxPresenter> checkboxFactory,
	IndirectProvider<ComboPresenter> comboFactory,
	IndirectProvider<ButtonPresenter> buttonFactory,	
	IndirectProvider<SearchPresenter> searchFactory) {
		
		this.form = form;
		this.SLOT= SLOT;
		this.eventBus=eventBus;
		this.textBoxFactory=textBoxFactory;
		this.dateFieldFactory=dateFieldFactory;
		this.numberFieldFactory=numberFieldFactory;
		this.checkboxFactory=checkboxFactory;
		this.comboFactory=comboFactory;
		this.buttonFactory=buttonFactory;
		this.searchFactory=searchFactory;
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

	
	public void createSearchField(final FieldModel field) {
		searchFactory.get(new ERPAsyncCallback<SearchPresenter>() {
			@Override
			public void processResult(SearchPresenter result) {
				result.setFieldModel(field);
				form.addToSlot(SLOT, result);				
			}
		});
	}

	public void createButtonField(final FieldModel field){
		createButtonField(field, false);
	}
	
	public void createButtonField(final FieldModel field, final boolean fireEvent) {
		buttonFactory.get(new ERPAsyncCallback<ButtonPresenter>() {
			@Override
			public void processResult(ButtonPresenter result) {
				result.setFieldModel(field);
				form.addToSlot(SLOT, result);
				
				if(fireEvent)
					eventBus.fireEvent(new ActionButtonCreatedEvent(field));
			}
		});

	}

	public void createComboField(final FieldModel field) {

		comboFactory.get(new ERPAsyncCallback<ComboPresenter>() {
			@Override
			public void processResult(ComboPresenter result) {
				result.setFieldModel(field);
				form.addToSlot(SLOT, result);
			}
		});

	}

	public void createCheckboxField(final FieldModel field) {
		checkboxFactory.get(new ERPAsyncCallback<CheckboxPresenter>() {
			@Override
			public void processResult(CheckboxPresenter result) {
				result.setFieldModel(field);
				form.addToSlot(SLOT, result);
			}
		});
	}

	public void createNumberField(final FieldModel field) {

		numberFieldFactory.get(new ERPAsyncCallback<NumberFieldPresenter>() {
			@Override
			public void processResult(NumberFieldPresenter result) {
				result.setFieldModel(field);
				form.addToSlot(SLOT, result);
			}
		});

	}

	public void createTextField(final FieldModel field) {

		textBoxFactory.get(new ERPAsyncCallback<TextFieldPresenter>() {
			public void processResult(TextFieldPresenter result) {
				result.setFieldModel(field);
				form.addToSlot(SLOT, result);
			}
		});

	}

	public void createDateField(final FieldModel field) {
		dateFieldFactory.get(new ERPAsyncCallback<DateFieldPresenter>() {
			@Override
			public void processResult(DateFieldPresenter result) {
				result.setFieldModel(field);
				form.addToSlot(SLOT, result);
			}
		});

	}
	

}
