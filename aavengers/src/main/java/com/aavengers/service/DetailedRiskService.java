package com.aavengers.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aavengers.DetailedRisk;
import com.aavengers.DetailedSeries;
import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import com.aavengers.data.ConflictIndexRepository;
import com.aavengers.data.CorruptionIndexRepository;
import com.aavengers.data.FreedomIndexRepository;
import com.aavengers.data.PositionRepository;
import com.aavengers.entity.BaseIndex;
import com.aavengers.entity.Position;

@Service
public class DetailedRiskService {

	public static Logger LOGGER = LoggerFactory.getLogger(DetailedRiskService.class);
	
	private static final int MAX_SECURITY_NAME_LENGTH = 15;
	private static final String PERFORMANCE = "Performance";
	
    @Autowired
    PositionRepository positionRepository;
    
    @Autowired
    CorruptionIndexRepository corruptionIndexRepository;

    @Autowired
    ConflictIndexRepository conflictIndexRepository;

    @Autowired
    FreedomIndexRepository freedomIndexRepository;

	@Autowired
	FreedomIndexRepository environemntIndexRepository;

    @Autowired
    ThresholdMappingService thresholdMappingService;

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
	public DetailedRisk detailedRiskForClient(IndicatorName indicator, IndicatorValue type, int year, String... accountNumbers) {
		
	    @SuppressWarnings("serial")
		Map<String, HTMLColour> colours = new HashMap<String, HTMLColour>() {{
	    	put(IndicatorValue.Poor.name(), new HTMLColour(249, 63, 38));
	    	put(IndicatorValue.Fair.name(), new HTMLColour(254, 163, 17));
	    	put(IndicatorValue.Good.name(), new HTMLColour(0, 133, 0));
	    	put(IndicatorValue.VeryGood.name(), new HTMLColour(106, 173, 228));
	    	put(IndicatorValue.Excellent.name(), new HTMLColour(0, 40, 136));
	    }};
	    
		
	    List<Position> positions = positionRepository.findByAcctNumIn(accountNumbers);
	    
	    Map<IndicatorName, List<? extends BaseIndex>> indexValues = new HashMap<>();
        indexValues.put(IndicatorName.Corruption, corruptionIndexRepository.findByYear(year));
        indexValues.put(IndicatorName.Conflict, conflictIndexRepository.findByYear(year));
        indexValues.put(IndicatorName.BusinessFreedom, freedomIndexRepository.findByYear(year));
		indexValues.put(IndicatorName.Environment, environemntIndexRepository.findByYear(year));
        
        List<? extends BaseIndex> indicatorList = indexValues.get(indicator);
        
        List<Position> result = new ArrayList<>();
        for(Position pos : positions) {
        	try {
            	if(type.equals(getIndicatorValueForPosition(indicator, indicatorList, pos))){
            		result.add(pos);
            	}
        	}
        	catch (NullPointerException npe) {
        		LOGGER.error("detailedRiskForClient():: exception: {}", npe.getMessage(), npe);
        	}
        }
	    
		DetailedRisk risk = new DetailedRisk();
		
		risk.setType("pie");
		
		Map<String, String> title = new HashMap<String, String>();
		title.put("text", PERFORMANCE + ": " + getTitle(type.name()));
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
		item.put("fontSize", new Integer(8));
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
		valueBox.put("fontColor", "#FFF");
		valueBox.put("fontWeight", "bold");
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
		tooltip.put("offsetR", new Integer(-60));
		tooltip.put("width", new Integer(70));
		tooltip.put("fontColor", "#fff");
		tooltip.put("borderRadius", new Integer(3));
		tooltip.put("bold", new Boolean(true));
		tooltip.put("align", "right");
		tooltip.put("fontSize", new Integer(10));
		risk.setTooltip(tooltip);
		
		Map<String, Object> scale = new HashMap<String, Object>();
		scale.put("sizeFactor", new Double(0.75));
		risk.setScale(scale);
		
		List<DetailedSeries> series = new ArrayList<DetailedSeries>();
		
		int incrementColour = 20;

		
		for(Position pos : result) {
			
			// calculate the colour shade, each slice of the inner pie is of the same base colour with a variation on shading
			HTMLColour htmlColour = colours.get(type.name());
			htmlColour.incrementColour(incrementColour);
			String colour = htmlColour.getHex();
			
			// create the series data to add to the series list of data
			DetailedSeries seriesItem = new DetailedSeries();
			
			List<BigDecimal> values = new ArrayList<BigDecimal>();
			values.add(pos.getMktVal());
			seriesItem.setValues(values);
			
			seriesItem.setText(pos.getSecurityName().substring(0, ((pos.getSecurityName().length() > MAX_SECURITY_NAME_LENGTH) ? MAX_SECURITY_NAME_LENGTH : pos.getSecurityName().length())));
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

	private IndicatorValue getIndicatorValueForPosition(IndicatorName indicator, List<? extends BaseIndex> indicatorList, Position pos) {
		if(IndicatorName.ReputationRisk.equals(indicator)){
			return thresholdMappingService.mapToValue(indicator, pos.getRepRisk().getValue());
		}
		for(BaseIndex idx : indicatorList) {
			if(idx.getCountry().equals(pos.getCountry().getCode())){
				return thresholdMappingService.mapToValue(indicator, new BigDecimal(idx.getVal()));
			}
		}
		return null;
	}
	
}
