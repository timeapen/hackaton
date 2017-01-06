package com.aavengers.rest;

import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import com.aavengers.Indicators;
import com.aavengers.data.IndicatorSettingsRepository;
import com.aavengers.entity.IndicatorSettings;

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
    @GetMapping(value = "/indicators/settings", produces = "application/json")
    public Iterable<IndicatorSettings> getIndicatorSettings() {
        Iterable<IndicatorSettings> result = indicatorSettingsRepository.findAll();
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
