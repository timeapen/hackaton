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
        int poorThreshold = 20;
        int remainingStep = (100 - poorThreshold) / 4;

        valueThresholds.put(new BigDecimal(poorThreshold), IndicatorValue.Poor);
        valueThresholds.put(new BigDecimal(poorThreshold + remainingStep), IndicatorValue.Fair);
        valueThresholds.put(new BigDecimal(poorThreshold + remainingStep * 2), IndicatorValue.Good);
        valueThresholds.put(new BigDecimal(poorThreshold + remainingStep * 3), IndicatorValue.VeryGood);
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
