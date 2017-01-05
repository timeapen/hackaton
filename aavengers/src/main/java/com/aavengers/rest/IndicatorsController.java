package com.aavengers.rest;

import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import com.aavengers.Indicators;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndicatorsController {

    @ResponseBody
    @GetMapping(value = "/indicators", produces = "application/json")
    public Indicators getIndicators() {
        Indicators result = new Indicators(IndicatorName.values(), IndicatorValue.values());
        return result;
    }
}
