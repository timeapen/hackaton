package com.aavengers;


import java.util.Map;

public class OverallRisk {
    Map<String, String> riskData;

    public OverallRisk(Map<String, String> riskData) {
        this.riskData = riskData;
    }

    public Map<String, String> getRiskData() {
        return riskData;
    }
}
