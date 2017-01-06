package com.aavengers.rest;

import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import com.aavengers.Indicators;
import com.aavengers.IndicatorsAcceptanceLevel;
import com.aavengers.service.ThresholdMappingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class IndicatorsController {

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
        return;
    }
}
