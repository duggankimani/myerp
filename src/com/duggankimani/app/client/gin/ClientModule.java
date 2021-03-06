package com.duggankimani.app.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.duggankimani.app.client.header.HeaderPresenter;
import com.duggankimani.app.client.header.HeaderView;
import com.duggankimani.app.client.menu.ApplicationMenuPresenter;
import com.duggankimani.app.client.menu.ApplicationMenuView;
import com.duggankimani.app.client.menu.FormMenuPresenter;
import com.duggankimani.app.client.menu.FormMenuView;
import com.duggankimani.app.client.place.ClientPlaceManager;
import com.duggankimani.app.client.core.MainPagePresenter;
import com.duggankimani.app.client.core.MainPageView;
import com.duggankimani.app.client.core.InputFormPresenter;
import com.duggankimani.app.client.core.InputFormView;
import com.duggankimani.app.client.place.DefaultPlace;
import com.duggankimani.app.client.place.NameTokens;
import com.duggankimani.app.client.components.TextFieldPresenter;
import com.duggankimani.app.client.components.TextFieldView;
import com.duggankimani.app.client.components.DateFieldPresenter;
import com.duggankimani.app.client.components.DateFieldView;
import com.duggankimani.app.client.workbench.FinanceWorkbenchPresenter;
import com.duggankimani.app.client.workbench.FinanceWorkbenchView;
import com.duggankimani.app.client.components.NumberFieldPresenter;
import com.duggankimani.app.client.components.NumberFieldView;
import com.duggankimani.app.client.components.CheckboxPresenter;
import com.duggankimani.app.client.components.CheckboxView;
import com.duggankimani.app.client.components.IDPresenter;
import com.duggankimani.app.client.components.IDView;
import com.duggankimani.app.client.components.ComboPresenter;
import com.duggankimani.app.client.components.ComboView;
import com.duggankimani.app.client.components.ButtonPresenter;
import com.duggankimani.app.client.components.ButtonView;
import com.duggankimani.app.client.core.ErrorPagePresenter;
import com.duggankimani.app.client.core.ErrorPageView;
import com.duggankimani.app.client.core.LinesPresenter;
import com.duggankimani.app.client.core.LinesView;
import com.duggankimani.app.client.core.TabsPresenter;
import com.duggankimani.app.client.core.TabsView;
import com.duggankimani.app.client.core.PopupFormPresenter;
import com.duggankimani.app.client.core.PopupFormView;
import com.duggankimani.app.client.core.FormPresenter;
import com.duggankimani.app.client.core.FormView;
import com.duggankimani.app.client.components.SearchPresenter;
import com.duggankimani.app.client.components.SearchView;
import com.duggankimani.app.client.core.ActionsPresenter;
import com.duggankimani.app.client.core.ActionsView;
import com.duggankimani.app.client.core.process.ProcessDialogPresenter;
import com.duggankimani.app.client.core.process.ProcessDialogView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindPresenter(InputFormPresenter.class, InputFormPresenter.MyView.class,
				InputFormView.class, InputFormPresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.financeworkbench);

		bindPresenterWidget(TextFieldPresenter.class,
				TextFieldPresenter.MyView.class, TextFieldView.class);

		bindPresenterWidget(DateFieldPresenter.class,
				DateFieldPresenter.MyView.class, DateFieldView.class);


		bindPresenter(FinanceWorkbenchPresenter.class,
				FinanceWorkbenchPresenter.MyView.class,
				FinanceWorkbenchView.class,
				FinanceWorkbenchPresenter.MyProxy.class);

		bindPresenterWidget(ApplicationMenuPresenter.class, ApplicationMenuPresenter.MyView.class,
				ApplicationMenuView.class);

		bindPresenterWidget(NumberFieldPresenter.class,
				NumberFieldPresenter.MyView.class, NumberFieldView.class);

		bindPresenterWidget(CheckboxPresenter.class, CheckboxPresenter.MyView.class,
				CheckboxView.class);

		bindPresenterWidget(IDPresenter.class, IDPresenter.MyView.class,
				IDView.class);

		bindPresenterWidget(ComboPresenter.class, ComboPresenter.MyView.class,
				ComboView.class);

		bindPresenterWidget(FormMenuPresenter.class,
				FormMenuPresenter.MyView.class, FormMenuView.class);

		bindPresenterWidget(ButtonPresenter.class,
				ButtonPresenter.MyView.class, ButtonView.class);

		bindPresenter(ErrorPagePresenter.class,
				ErrorPagePresenter.MyView.class, ErrorPageView.class,
				ErrorPagePresenter.MyProxy.class);

		bindPresenterWidget(LinesPresenter.class,
				LinesPresenter.MyView.class, LinesView.class);

		bindPresenterWidget(TabsPresenter.class,
				TabsPresenter.MyView.class, TabsView.class);

		bindPresenterWidget(PopupFormPresenter.class,
				PopupFormPresenter.IPopupView.class, PopupFormView.class);

		bindPresenterWidget(FormPresenter.class, FormPresenter.MyView.class,
				FormView.class);

		bindPresenterWidget(SearchPresenter.class,
				SearchPresenter.MyView.class, SearchView.class);
		
		bindPresenterWidget(HeaderPresenter.class,
				HeaderPresenter.MyView.class , HeaderView.class);

		bindPresenterWidget(ActionsPresenter.class,
				ActionsPresenter.IActionsView.class, ActionsView.class);

		bindPresenterWidget(ProcessDialogPresenter.class,
				ProcessDialogPresenter.IProcessDialog.class, ProcessDialogView.class);

	}
}
