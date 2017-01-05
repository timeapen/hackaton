package com.aavengers;

public class Indicators {
    IndicatorName[] indicators;
    IndicatorValue[] valueThresholds;

    public Indicators(IndicatorName[] indicators, IndicatorValue[] valueThresholds) {
        this.indicators = indicators;
        this.valueThresholds = valueThresholds;
    }

    public IndicatorName[] getIndicators() {
        return indicators;
    }

    public IndicatorValue[] getValueThresholds() {
        return valueThresholds;
    }
}
