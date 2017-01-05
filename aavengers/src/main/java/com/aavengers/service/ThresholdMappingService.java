package com.aavengers.service;


import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.TreeMap;

@Service
public class ThresholdMappingService {

    private static TreeMap<BigDecimal, IndicatorValue> valueThresholds;
    static {
        valueThresholds = new TreeMap();
        valueThresholds.put(new BigDecimal(20), IndicatorValue.Poor);
        valueThresholds.put(new BigDecimal(40), IndicatorValue.Fair);
        valueThresholds.put(new BigDecimal(60), IndicatorValue.Good);
        valueThresholds.put(new BigDecimal(80), IndicatorValue.Very_Good);
        valueThresholds.put(new BigDecimal(100), IndicatorValue.Excellent);
    }

    public IndicatorValue mapToValue(IndicatorName indicatorName, BigDecimal indicatorValue) {
        BigDecimal normalizedValue = normalize(indicatorName, indicatorValue);
        for(BigDecimal t : valueThresholds.keySet()) {
            if (normalizedValue.compareTo(t) <= 0) {
                return valueThresholds.get(t);
            }
        }
        return null;
    }

    private BigDecimal normalize(IndicatorName indicatorName, BigDecimal indicatorValue) {
        if(indicatorName.equals(IndicatorName.Conflict)) {
            return new BigDecimal(100).subtract(indicatorValue);
        }
        return indicatorValue;
    }
}
