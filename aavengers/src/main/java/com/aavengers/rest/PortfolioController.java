package com.aavengers.rest;

import com.aavengers.*;
import com.aavengers.data.ConflictIndexRepository;
import com.aavengers.data.CorruptionIndexRepository;
import com.aavengers.data.PositionRepository;
import com.aavengers.entity.BaseIndex;
import com.aavengers.entity.Position;
import com.aavengers.service.DetailedRiskService;
import com.aavengers.service.IndicatorRiskService;
import com.aavengers.service.RiskService;
import com.aavengers.service.ThresholdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/portfolio")
@CrossOrigin
public class PortfolioController {

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
        return indicatorRiskService.getIndicatorRisk(indicator, 2016, accountNumber.split("\\|"));
    }
    
    @ResponseBody
    @GetMapping(value = "/detailedRisk/{accountNumber}/{type}/{indicator}", produces = "application/json")
    public DetailedRisk getDetailedRisk(@PathVariable String accountNumber, @PathVariable String type, @PathVariable String indicator) {
    	return detailedRiskService.detailedRiskForClient(accountNumber, type, indicator);
    }
    
}
