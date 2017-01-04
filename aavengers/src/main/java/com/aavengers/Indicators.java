package com.aavengers;

/**
 * Created by admin on 2017-01-04.
 */
public class Indicators {
    String[] indicators;
    String[] valueTresholds;

    public Indicators(String[] indicators, String[] valueTresholds) {
        this.indicators = indicators;
        this.valueTresholds = valueTresholds;
    }

    public String[] getIndicators() {
        return indicators;
    }

    public String[] getValueTresholds() {
        return valueTresholds;
    }
}
