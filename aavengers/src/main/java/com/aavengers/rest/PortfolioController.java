package com.aavengers.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aavengers.DetailedRisk;
import com.aavengers.IndicatorName;
import com.aavengers.IndicatorRisk;
import com.aavengers.IndicatorValue;
import com.aavengers.OverallRisk;
import com.aavengers.service.DetailedRiskService;
import com.aavengers.service.IndicatorRiskService;
import com.aavengers.service.RiskService;

@Controller
@RequestMapping("/portfolio")
@CrossOrigin
public class PortfolioController {

	public static Logger LOGGER = LoggerFactory.getLogger(PortfolioController.class);
	
    @Autowired
    RiskService riskService;

    @Autowired
    IndicatorRiskService indicatorRiskService;

    @Autowired
    DetailedRiskService detailedRiskService;
    
    @ResponseBody
    @GetMapping(value = "/overallRisk/{accountNumber}", produces = "application/json")
    public OverallRisk getOverallRisk(@PathVariable String accountNumber) {
        return riskService.getOverallRisk(2016, accountNumber.split("\\|"));
    }
    
    @ResponseBody
    @GetMapping(value = "/indicatorRisk/{indicator}/{accountNumber}", produces = "application/json")
    public List<IndicatorRisk> getIndicatorRisk(@PathVariable String indicator, @PathVariable String accountNumber) {
    	LOGGER.info("getIndicatorRisk():: indicator: {}, accountNumber: {}", indicator, accountNumber);
        return indicatorRiskService.getIndicatorRisk(IndicatorName.valueOf(indicator), 2016, accountNumber.split("\\|"));
    }
    
    @ResponseBody
    @GetMapping(value = "/detailedRisk/{indicator}/{type}/{accountNumber}", produces = "application/json")
    public DetailedRisk getDetailedRisk(@PathVariable String accountNumber, @PathVariable String type, @PathVariable String indicator) {
    	return detailedRiskService.detailedRiskForClient(IndicatorName.valueOf(indicator), IndicatorValue.valueOf(type), 2016, accountNumber.split("\\|"));
    }
    
}
