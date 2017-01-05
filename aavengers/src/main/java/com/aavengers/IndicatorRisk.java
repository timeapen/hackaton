package com.aavengers;

import java.math.BigDecimal;

public class IndicatorRisk {

	IndicatorValue type;
	BigDecimal amount;
	String title;
	
	public IndicatorRisk(IndicatorValue type, BigDecimal amount) {
		super();
		this.type = type;
		this.amount = amount;
		this.title = type.title();
	}
	
	public IndicatorValue getType() {
		return type;
	}
	
	public void setType(IndicatorValue type) {
		this.type = type;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
