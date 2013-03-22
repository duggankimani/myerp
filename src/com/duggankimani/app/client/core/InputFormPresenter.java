package com.duggankimani.app.client.core;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.duggankimani.app.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.duggankimani.app.client.core.MainPagePresenter;
import com.duggankimani.app.client.events.AfterFormLoadEvent;
import com.duggankimani.app.client.events.AfterFormLoadEvent.AfterFormLoadHandler;
import com.duggankimani.app.shared.action.GetWindowAction;

/**
 * Addressable Input Form
 * @author duggan
 *
 */
public class InputFormPresenter extends
		Presenter<InputFormPresenter.MyView, InputFormPresenter.MyProxy> implements AfterFormLoadHandler{

	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.inputfrm)
	public interface MyProxy extends ProxyPlace<InputFormPresenter> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> FORM_SLOT = new Type<RevealContentHandler<?>>();

	@Inject FormPresenter formPresenter;

	
	@Inject
	public InputFormPresenter(final EventBus eventBus, final MyView view,
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

	@Override
	protected void onUnbind() {
		super.onUnbind();
	}
	
	/**
	 * true to Control when the window becomes visible
	 * - Important because windows may become visible before the
	 * fields & data has been loaded 
	 */
	@Override
	public boolean useManualReveal() {
		return false;
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		this.setInSlot(FORM_SLOT, null);

		String AD_Menu_ID = request.getParameter("m", "0");
		String AD_Window_ID=request.getParameter("w", "0");
		String tabNo = request.getParameter("t", "0");
		String recordid=request.getParameter("r", "0");
		

		final GetWindowAction action = new GetWindowAction(new Integer(AD_Menu_ID), new Integer(AD_Window_ID), new Integer(tabNo), new Integer(recordid), -1);
		formPresenter.setAction(action);
		formPresenter.setViewMode(0);
		InputFormPresenter.this.setInSlot(FORM_SLOT, formPresenter);
	}

	@Override
	protected void onReset() {
		super.onReset();
		setInSlot(FORM_SLOT, formPresenter);

	}

	@Override
	public void onAfterFormLoad(AfterFormLoadEvent event) {
		if(event.getSource()==formPresenter){
			if(event.isSuccess()){
				//getProxy().manualReveal(this);
			}
			else{
				//getProxy().manualRevealFailed();
			}
		}
	}

}
