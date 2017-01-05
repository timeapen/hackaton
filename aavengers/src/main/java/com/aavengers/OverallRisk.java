package com.aavengers;


import java.util.Map;

public class OverallRisk {
    Map<IndicatorName, IndicatorValue> riskData;

    public OverallRisk(Map<IndicatorName, IndicatorValue> riskData) {
        this.riskData = riskData;
    }

    public Map<IndicatorName, IndicatorValue> getRiskData() {
        return riskData;
    }
}
