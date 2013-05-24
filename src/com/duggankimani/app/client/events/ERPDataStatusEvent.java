package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Boolean;
import java.lang.String;
import com.google.gwt.event.shared.HasHandlers;

public class ERPDataStatusEvent extends
		GwtEvent<ERPDataStatusEvent.ERPDataStatusHandler> {

	public static Type<ERPDataStatusHandler> TYPE = new Type<ERPDataStatusHandler>();
	private Boolean isWarning;
	private Boolean isError;
	private Boolean isInserting;
	private String isChanged;
	private String isFirstRow;
	private String isLastRow;
	private String info;
	private String messageKey;
	private String changedColumnName;
	private String message;

	public interface ERPDataStatusHandler extends EventHandler {
		void onERPDataStatus(ERPDataStatusEvent event);
	}

	public ERPDataStatusEvent(Boolean isWarning, Boolean isError,
			Boolean isInserting, String isChanged, String isFirstRow,
			String isLastRow, String info, String messageKey,
			String changedColumnName, String message) {
		this.isWarning = isWarning;
		this.isError = isError;
		this.isInserting = isInserting;
		this.isChanged = isChanged;
		this.isFirstRow = isFirstRow;
		this.isLastRow = isLastRow;
		this.info = info;
		this.messageKey = messageKey;
		this.changedColumnName = changedColumnName;
		this.message = message;
	}

	public Boolean getIsWarning() {
		return isWarning;
	}

	public Boolean getIsError() {
		return isError;
	}

	public Boolean getIsInserting() {
		return isInserting;
	}

	public String getIsChanged() {
		return isChanged;
	}

	public String getIsFirstRow() {
		return isFirstRow;
	}

	public String getIsLastRow() {
		return isLastRow;
	}

	public String getInfo() {
		return info;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public String getChangedColumnName() {
		return changedColumnName;
	}

	public String getMessage() {
		return message;
	}

	@Override
	protected void dispatch(ERPDataStatusHandler handler) {
		handler.onERPDataStatus(this);
	}

	@Override
	public Type<ERPDataStatusHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ERPDataStatusHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Boolean isWarning, Boolean isError,
			Boolean isInserting, String isChanged, String isFirstRow,
			String isLastRow, String info, String messageKey,
			String changedColumnName, String message) {
		source.fireEvent(new ERPDataStatusEvent(isWarning, isError, isInserting,
				isChanged, isFirstRow, isLastRow, info, messageKey,
				changedColumnName, message));
	}
}
