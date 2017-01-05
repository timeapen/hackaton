package com.aavengers.service;

import com.aavengers.IndicatorName;
import com.aavengers.IndicatorValue;
import com.aavengers.OverallRisk;
import com.aavengers.data.ConflictIndexRepository;
import com.aavengers.data.CorruptionIndexRepository;
import com.aavengers.data.PositionRepository;
import com.aavengers.entity.BaseIndex;
import com.aavengers.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
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
    ThresholdMappingService thresholdMappingService;

    public OverallRisk getOverallRisk(String... accountNumbers) {

        Map<IndicatorName, IndicatorValue> data = new HashMap<>();
        List<Position> positions = positionRepository.findByAcctNumIn(accountNumbers);

        data.put(IndicatorName.Corruption, thresholdMappingService.mapToValue(IndicatorName.Corruption, getCorruptionIndexValue(positions)));
        data.put(IndicatorName.Conflict, thresholdMappingService.mapToValue(IndicatorName.Conflict, getConflictIndexValue(positions)));

        return new OverallRisk(data);
    }

    private BigDecimal getCorruptionIndexValue(List<Position> positions) {
        return averageValue(positions, corruptionIndexRepository.findByYear(2016));
    }

    private BigDecimal getConflictIndexValue(List<Position> positions) {
        return averageValue(positions, conflictIndexRepository.findByYear(2016));
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

        return calculatedTotal.divide(totalMarketValue);
    }
}
