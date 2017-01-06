package com.aavengers.service;

import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import com.aavengers.OverallRisk;
import com.aavengers.data.*;
import com.aavengers.entity.BaseIndex;
import com.aavengers.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RiskService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    CorruptionIndexRepository corruptionIndexRepository;

    @Autowired
    ConflictIndexRepository conflictIndexRepository;

    @Autowired
    FreedomIndexRepository freedomIndexRepository;

    @Autowired
    EnvironmentIndexRepository environmentIndexRepository;

    @Autowired
    ThresholdMappingService thresholdMappingService;

    public OverallRisk getOverallRisk(int year, String... accountNumbers) {

        Map<IndicatorName, IndicatorValue> data = new HashMap<>();
        List<Position> positions = positionRepository.findByAcctNumIn(accountNumbers);

        data.put(IndicatorName.Corruption, thresholdMappingService.mapToValue(IndicatorName.Corruption, getCorruptionIndexValue(year, positions)));
        data.put(IndicatorName.Conflict, thresholdMappingService.mapToValue(IndicatorName.Conflict, getConflictIndexValue(year, positions)));
        data.put(IndicatorName.BusinessFreedom, thresholdMappingService.mapToValue(IndicatorName.BusinessFreedom, getFreedomIndexValue(year, positions)));
        data.put(IndicatorName.Environment, thresholdMappingService.mapToValue(IndicatorName.Environment, getEnvironmentIndexValue(year, positions)));
        data.put(IndicatorName.ReputationRisk, thresholdMappingService.mapToValue(IndicatorName.ReputationRisk, getRepRiskIndexValue(year, positions)));

        return new OverallRisk(data);
    }

    private BigDecimal getCorruptionIndexValue(int year, List<Position> positions) {
        return averageValue(positions, corruptionIndexRepository.findByYear(year));
    }

    private BigDecimal getConflictIndexValue(int year, List<Position> positions) {
        return averageValue(positions, conflictIndexRepository.findByYear(year));
    }

    private BigDecimal getFreedomIndexValue(int year, List<Position> positions) {
        return averageValue(positions, freedomIndexRepository.findByYear(year));
    }

    private BigDecimal getEnvironmentIndexValue(int year, List<Position> positions) {
        return averageValue(positions, environmentIndexRepository.findByYear(year));
    }

    private BigDecimal getRepRiskIndexValue(int year, List<Position> positions) {
        BigDecimal calculatedTotal = BigDecimal.ZERO;
        BigDecimal totalMarketValue = BigDecimal.ZERO;

        for(Position pos : positions) {
            BigDecimal posCorruptionIdx = pos.getRepRisk().getValue();
            if(posCorruptionIdx == null) {
                continue;
            }
            calculatedTotal = calculatedTotal.add(posCorruptionIdx.multiply(pos.getMktVal()));
            totalMarketValue = totalMarketValue.add(pos.getMktVal());
        }
        return calculatedTotal.divide(totalMarketValue, 5, RoundingMode.HALF_UP);
    }

    private BigDecimal averageValue(List<Position> positions, List<? extends BaseIndex> corruptionIdx) {
        Map<String, BigDecimal> corruptionIdxMap = new HashMap<>();
        BigDecimal calculatedTotal = BigDecimal.ZERO;
        BigDecimal totalMarketValue = BigDecimal.ZERO;

        for(BaseIndex idx : corruptionIdx) {
            corruptionIdxMap.put(idx.getCountry(), new BigDecimal(idx.getVal()));
        }

        for(Position pos : positions) {
            BigDecimal posCorruptionIdx = corruptionIdxMap.get(pos.getCountry().getCode());
            if(posCorruptionIdx == null) {
                continue;
            }
            calculatedTotal = calculatedTotal.add(posCorruptionIdx.multiply(pos.getMktVal()));
            totalMarketValue = totalMarketValue.add(pos.getMktVal());
        }

        return calculatedTotal.divide(totalMarketValue, 5, RoundingMode.HALF_UP);
    }
}
