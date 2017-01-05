package com.aavengers;

public enum IndicatorValue {
    Poor("Poor"), Fair("Fair"), Good("Good"), VeryGood("Very Good"), Excellent("Excellent");
	
	private String title;
	
	IndicatorValue(String title) {
		this.title = title;
	}
	
	public String title() {
		return title;
	}
    
}

