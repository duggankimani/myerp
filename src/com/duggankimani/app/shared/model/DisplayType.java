package com.duggankimani.app.shared.model;

public enum DisplayType {

	STRING(10,"Display Type 10	String"),
	INTEGER(11,"Display Type 11 Integer"),
	AMOUNT(12, "Display Type 12	Amount"),
	ID(13,"Display Type 13	ID"),
	TEXT(14,"Display Type 14 Text"),	
	DATE(15,"Display Type 15 Date"),
	DATETIME(16,"Display Type 16 DateTime"),
	LIST(17,"Display Type 17 List"),
	TABLE(18,"Display Type 18 Table"),
	TABLEDIR(19,"Display Type 19 TableDir"),
	YESNO(20,"Display Type 20 YN"),
	LOCATION(21,"Display Type 21 Location"),
	NUMBER(22,"Display Type 22	Number"),
	BLOB(23,"Display Type 23 BLOB"),
	TIME(24, "Display Type 24 Time"),
	ROWID(26, "Display Type 26	RowID"),
	ACCOUNT(25,"Display Type 25 Account"),
	COLOR(27,"Display Type 27	Color"),
	BUTTON(28,"Display Type 28	Button"),
	QUANTITY(29,"Display Type 29 Quantity"),
	SEARCH(30,"Display Type 30	Search"),
	LOCATOR(31,"Display Type 31 Locator");
	
	
	String description;
	int id;
	
	private DisplayType(int id, String description){
		this.description = description;
		this.id=id;
	}
	
	private DisplayType(){
	
	}
	
	public static DisplayType getDisplayType(int id){
		for(DisplayType display : DisplayType.values()){
			if(display.id==id)
				return display;
		}
		
		return DisplayType.STRING;
	}
	
	public Boolean equals(DisplayType type){
		return this.equals(type);
	}

	public boolean isLookup() {
		
		boolean isLookup = (this == LIST || this == TABLE
				|| this == TABLEDIR || this == SEARCH);
		
		return isLookup;
	}
}
