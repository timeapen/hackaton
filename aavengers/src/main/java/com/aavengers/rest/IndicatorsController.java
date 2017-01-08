package com.aavengers.rest;

import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import com.aavengers.Indicators;
import com.aavengers.data.IndicatorSettingsRepository;
import com.aavengers.entity.IndicatorSettings;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aavengers.IndicatorsAcceptanceLevel;
import com.aavengers.service.ThresholdMappingService;

@Controller
@CrossOrigin
public class IndicatorsController {

	@Autowired
    IndicatorSettingsRepository indicatorSettingsRepository;
	
    @ResponseBody
    @GetMapping(value = "/indicators", produces = "application/json")
    public Indicators getIndicators() {
        Indicators result = new Indicators(IndicatorName.values(), IndicatorValue.values());
        return result;
    }
    
    @ResponseBody
    @GetMapping(value = "/indicators/acceptance", produces = "application/json")
    public IndicatorsAcceptanceLevel getIndicatorAcceptanceLevel() {
        return new IndicatorsAcceptanceLevel(ThresholdMappingService.getAcceptanceThresholds());
    }

    @ResponseBody
    @PostMapping(value = "/indicators/acceptance", consumes = "application/json")
    public void storeIndicators(@RequestBody IndicatorsAcceptanceLevel levels) {
        for(String indicator : levels.getLevels().keySet()) {
            ThresholdMappingService.updateThreshold(IndicatorName.valueOf(indicator), levels.getLevels().get(indicator));
        }
    }
    
    @ResponseBody
    @GetMapping(value = "/indicators/settings", produces = "application/json")
    public Iterable<IndicatorSettings> getIndicatorSettings() {
    	List<IndicatorSettings> result = new LinkedList<IndicatorSettings>();
    	
    	for(IndicatorName indicatorNameOrdered : IndicatorName.values()) {
    		result.add(indicatorSettingsRepository.findByIndicatorName(indicatorNameOrdered.name()));
    	}
    	
    	return result;
    }
    
    @PostMapping(value = "/indicators/settings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IndicatorSettings> updateIndicatorSetting(@RequestBody IndicatorSettings indicatorSetting) {
    	
    	IndicatorSettings toUpdate = indicatorSettingsRepository.findOne(indicatorSetting.getIndicatorName());
    	toUpdate.setIndicatorValue(indicatorSetting.getIndicatorValue());
    	indicatorSettingsRepository.save(toUpdate);
    	
    	return new ResponseEntity<IndicatorSettings>(toUpdate, HttpStatus.OK);
    }
    
}
