package com.aavengers;

import java.util.List;
import java.util.Map;

public class DetailedRisk {

	String type;
	Map<String, String> title;
	Map<String, Object> legend;
	Map<String, Object> plot;
	Map<String, Object> tooltip;
	Map<String, Object> scale;
	List<DetailedSeries> series;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getTitle() {
		return title;
	}

	public void setTitle(Map<String, String> title) {
		this.title = title;
	}

	public Map<String, Object> getLegend() {
		return legend;
	}

	public void setLegend(Map<String, Object> legend) {
		this.legend = legend;
	}

	public Map<String, Object> getPlot() {
		return plot;
	}

	public void setPlot(Map<String, Object> plot) {
		this.plot = plot;
	}

	public Map<String, Object> getTooltip() {
		return tooltip;
	}

	public void setTooltip(Map<String, Object> tooltip) {
		this.tooltip = tooltip;
	}

	public Map<String, Object> getScale() {
		return scale;
	}

	public void setScale(Map<String, Object> scale) {
		this.scale = scale;
	}

	public List<DetailedSeries> getSeries() {
		return series;
	}

	public void setSeries(List<DetailedSeries> series) {
		this.series = series;
	}
	
}
