package com.duggankimani.app.client.workbench;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.duggankimani.app.client.place.DefaultPlace;
import com.duggankimani.app.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.duggankimani.app.client.core.MainPagePresenter;

public class FinanceWorkbenchPresenter
		extends
		Presenter<FinanceWorkbenchPresenter.MyView, FinanceWorkbenchPresenter.MyProxy> {

	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.financeworkbench)
	public interface MyProxy extends ProxyPlace<FinanceWorkbenchPresenter> {
	}

	@Inject
	public FinanceWorkbenchPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.SLOT_content, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
