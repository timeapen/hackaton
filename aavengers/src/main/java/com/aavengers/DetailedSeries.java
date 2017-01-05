package com.aavengers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DetailedSeries {

	List<BigDecimal> values;
	String text;
	String backgroundColor;
	String legendText;
	
	Map<String, Object> legendMarker;
	Map<String, Object> legendItem;
	Map<String, Object> tooltip;
	
	
	public List<BigDecimal> getValues() {
		return values;
	}
	public void setValues(List<BigDecimal> values) {
		this.values = values;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getLegendText() {
		return legendText;
	}
	public void setLegendText(String legendText) {
		this.legendText = legendText;
	}
	public Map<String, Object> getLegendMarker() {
		return legendMarker;
	}
	public void setLegendMarker(Map<String, Object> legendMarker) {
		this.legendMarker = legendMarker;
	}
	public Map<String, Object> getLegendItem() {
		return legendItem;
	}
	public void setLegendItem(Map<String, Object> legendItem) {
		this.legendItem = legendItem;
	}
	public Map<String, Object> getTooltip() {
		return tooltip;
	}
	public void setTooltip(Map<String, Object> tooltip) {
		this.tooltip = tooltip;
	}
	
}
