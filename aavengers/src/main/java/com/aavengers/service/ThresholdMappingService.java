package com.aavengers.service;


import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ThresholdMappingService {

    private static Map<IndicatorName, TreeMap<BigDecimal, IndicatorValue>> valueThresholds;
    static {
        valueThresholds = new HashMap<>();
        updateThreshold(IndicatorName.Conflict, 20);
        updateThreshold(IndicatorName.Corruption, 20);
        updateThreshold(IndicatorName.BusinessFreedom, 20);
        updateThreshold(IndicatorName.Environment, 20);
        updateThreshold(IndicatorName.ReputationRisk, 20);
    }

    public static void updateThreshold(IndicatorName indicatorName, int acceptanceThreshold) {
        TreeMap indValueThresholds = new TreeMap();
        int remainingStep = (100 - acceptanceThreshold) / 4;

        indValueThresholds.put(new BigDecimal(acceptanceThreshold), IndicatorValue.Poor);
        indValueThresholds.put(new BigDecimal(acceptanceThreshold + remainingStep), IndicatorValue.Fair);
        indValueThresholds.put(new BigDecimal(acceptanceThreshold + remainingStep * 2), IndicatorValue.Good);
        indValueThresholds.put(new BigDecimal(acceptanceThreshold + remainingStep * 3), IndicatorValue.VeryGood);
        indValueThresholds.put(new BigDecimal(100), IndicatorValue.Excellent);

        valueThresholds.put(indicatorName, indValueThresholds);
    }

    public static Map<String, Integer> getAcceptanceThresholds(){
        Map<String, Integer> result = new HashMap<>();
        for(IndicatorName ind : valueThresholds.keySet()) {
            result.put(ind.name(), (valueThresholds.get(ind).keySet().iterator().next()).intValue());
        }
        return result;
    }

    public IndicatorValue mapToValue(IndicatorName indicatorName, BigDecimal indicatorValue) {
        BigDecimal normalizedValue = normalize(indicatorName, indicatorValue);
        for(BigDecimal t : valueThresholds.get(indicatorName).keySet()) {
            if (normalizedValue.compareTo(t) <= 0) {
                return valueThresholds.get(indicatorName).get(t);
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
