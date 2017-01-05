package com.aavengers.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aavengers.DetailedRisk;
import com.aavengers.DetailedSeries;
import com.aavengers.IndicatorValue;
import com.aavengers.data.PositionRepository;

@Service
public class DetailedRiskService {

    @Autowired
    PositionRepository positionRepository;

    @SuppressWarnings("serial")
	Map<String, String> title = new HashMap<String, String>() {{
    	put(IndicatorValue.Poor.name(), IndicatorValue.Poor.title());
    	put(IndicatorValue.Fair.name(), IndicatorValue.Fair.title());
    	put(IndicatorValue.Good.name(), IndicatorValue.Good.title());
    	put(IndicatorValue.VeryGood.name(), IndicatorValue.VeryGood.title());
    	put(IndicatorValue.Excellent.name(), IndicatorValue.Excellent.title());
    }};
    
    private String getTitle(String type) {
    	if(StringUtils.isEmpty(type)) {
    		return "Position";
    	}
    	return title.get(type);
    }
    
    public class HTMLColour {
    	private int red;
    	private int green;
    	private int blue;
    	
    	public HTMLColour(int red, int green, int blue) {
    		this.red = red;
    		this.green = green;
    		this.blue = blue;
    	}
    	
    	public void incrementColour(int incrementAll) {
    		incrementColour(incrementAll, incrementAll, incrementAll);
    	}
    	
    	public void incrementColour(int red, int green, int blue) {
    		this.red = increment(this.red, red);
    		this.green = increment(this.green, green);
    		this.blue = increment(this.blue, blue);
    	}
    	
    	private int increment(int current, int increment) {
    		int RGB_MAX = 255;
    		int sum = current + increment;
    		if(sum > RGB_MAX) {
    			return RGB_MAX;
    		}
    		return sum;
    	}
    	
    	public String getHex() {
    		return String.format("#%02x%02x%02x", red, green, blue);
    	}
    	
    }
    
    /**
     * Obtain the inner details (securities) for an account, risk level and type of indicator
     * @param accountNumber the account number
     * @param type the risk level (ex. RISK, NEAR, SAFE)
     * @param indicator the attribute of calculation (ex. Corruption, Environmental, Corporate Governence, Liquidity, etc.)
     * @return DetailedRisk that comprises all the securities
     */
	public DetailedRisk detailedRiskForClient(String accountNumber, String type, String indicator) {
		
	    @SuppressWarnings("serial")
		Map<String, HTMLColour> colours = new HashMap<String, HTMLColour>() {{
	    	put(IndicatorValue.Poor.name(), new HTMLColour(249, 63, 38));
	    	put(IndicatorValue.Fair.name(), new HTMLColour(254, 223, 1));
	    	put(IndicatorValue.Good.name(), new HTMLColour(0, 133, 0));
	    	put(IndicatorValue.VeryGood.name(), new HTMLColour(106, 173, 228));
	    	put(IndicatorValue.Excellent.name(), new HTMLColour(0, 40, 136));
	    }};
	    
		
	    // @TODO: RG, obtain data from backend
	    
		DetailedRisk risk = new DetailedRisk();
		
		risk.setType("pie");
		
		Map<String, String> title = new HashMap<String, String>();
		title.put("text", getTitle(type));
		title.put("align", "right");
		title.put("fontColor", "#616161");
		risk.setTitle(title);
		
		Map<String, Object> legend = new HashMap<String, Object>();
		legend.put("text", "%t<br>");
		legend.put("width", new Integer(120));
		legend.put("verticalAlign", "middle");
		legend.put("borderWidth", new Integer(0));
		legend.put("toggleAction", "remove");
		
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("padding", new Integer(5));
		item.put("borderRadius", new Integer(3));
		item.put("fontColor", "#fff");
		item.put("align", "right");
		item.put("width", new Integer(100));
		legend.put("item", item);
		
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("text", "Total $");
		header.put("align", "right");
		header.put("fontSize", new Integer(13));
		header.put("bold", new Boolean(true));
		header.put("fontColor", "#616161");
		legend.put("header", header);
		
		Map<String, Object> itemOff = new HashMap<String, Object>();
		itemOff.put("alpha", new Double(0.7));
		itemOff.put("textAlpha", new Integer(1));
		itemOff.put("fontColor", "#616161");
		itemOff.put("textDecoration", "line-through");
		itemOff.put("lineWidth", new Integer(2));
		legend.put("itemOff", itemOff);
		
		Map<String, Object> markerOff = new HashMap<String, Object>();
		markerOff.put("alpha", new Double(0.2));
		legend.put("markerOff", markerOff);

		risk.setLegend(legend);

		Map<String, Object> plot = new HashMap<String, Object>();
		plot.put("refAngle", new Integer(270));
		plot.put("decimals", new Integer(0));
		plot.put("thousandsSeparator", ",");
		
		Map<String, Object> valueBox = new HashMap<String, Object>();
		valueBox.put("decimals", new Integer(1));
		valueBox.put("fontSize", new Integer(10));
		plot.put("valueBox", valueBox);
		
		Map<String, Object> animation = new HashMap<String, Object>();
		animation.put("effect", new Integer(3));
		animation.put("method", new Integer(1));
		animation.put("sequence", new Integer(1));
		animation.put("onLegendToggle", new Boolean(false));
		plot.put("animation", animation);
		
		risk.setPlot(plot);
		
		Map<String, Object> tooltip = new HashMap<String, Object>();
		tooltip.put("text", "%t<br>$%v");
		tooltip.put("placement", "node:out");
		tooltip.put("offsetR", new Integer(10));
		tooltip.put("width", new Integer(110));
		tooltip.put("fontColor", "#fff");
		tooltip.put("borderRadius", new Integer(3));
		tooltip.put("bold", new Boolean(true));
		tooltip.put("align", "right");
		risk.setTooltip(tooltip);
		
		Map<String, Object> scale = new HashMap<String, Object>();
		scale.put("sizeFactor", new Double(0.75));
		risk.setScale(scale);
		
		List<DetailedSeries> series = new ArrayList<DetailedSeries>();
		
		int incrementColour = 20;
		// @TODO: RG, replace with values from data store
		for(int i=0; i < 3; i++) {
			
			// calculate the colour shade, each slice of the inner pie is of the same base colour with a variation on shading
			HTMLColour htmlColour = colours.get(type);
			htmlColour.incrementColour(incrementColour);
			String colour = htmlColour.getHex();
			
			// create the series data to add to the series list of data
			DetailedSeries seriesItem = new DetailedSeries();
			
			List<Long> values = new ArrayList<Long>();
			// @TODO: RG, obtain the total amount of particular security
			values.add(new Long(25684578*(i+1)));
			seriesItem.setValues(values);
			
			// @TODO: RG, obtain the security name
			seriesItem.setText("Security #" + (i+1));
			seriesItem.setBackgroundColor(colour);
			seriesItem.setLegendText("%t<br><b>$%v</b>");
			
			Map<String, Object> legendItem = new HashMap<String, Object>();
			legendItem.put("backgroundColor", colour);
			seriesItem.setLegendItem(legendItem);
			
			Map<String, Object> tooltipSeriesItem = new HashMap<String, Object>();
			tooltipSeriesItem.put("backgroundColor", colour);
			seriesItem.setTooltip(tooltipSeriesItem);
			
			Map<String, Object> legendMarker = new HashMap<String, Object>();
			legendMarker.put("type", "circle");
			legendMarker.put("size", new Integer(7));
			legendMarker.put("borderColor", colour);
			legendMarker.put("borderWidth", new Integer(4));
			legendMarker.put("backgroundColor", "#fff");
			
			seriesItem.setLegendMarker(legendMarker);
			
			series.add(seriesItem);
		}
		
		risk.setSeries(series);
		
		return risk;
	}
	
}
