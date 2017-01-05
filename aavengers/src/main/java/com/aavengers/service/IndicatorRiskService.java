package com.aavengers.service;

import com.aavengers.IndicatorName;
import com.aavengers.IndicatorRisk;
import com.aavengers.IndicatorValue;
import com.aavengers.OverallRisk;
import com.aavengers.data.ConflictIndexRepository;
import com.aavengers.data.CorruptionIndexRepository;
import com.aavengers.data.FreedomIndexRepository;
import com.aavengers.data.PositionRepository;
import com.aavengers.entity.BaseIndex;
import com.aavengers.entity.CorruptionIndex;
import com.aavengers.entity.Country;
import com.aavengers.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndicatorRiskService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    CorruptionIndexRepository corruptionIndexRepository;

    @Autowired
    ConflictIndexRepository conflictIndexRepository;

    @Autowired
    FreedomIndexRepository freedomIndexRepository;

    @Autowired
    ThresholdMappingService thresholdMappingService;

    public List<IndicatorRisk> getIndicatorRisk(String indicatorName, int year, String... accountNumbers) {
    	
    	LinkedHashMap<IndicatorValue, BigDecimal> data = new LinkedHashMap<>();
        data.put(IndicatorValue.Poor, BigDecimal.ZERO);
        data.put(IndicatorValue.Fair, BigDecimal.ZERO);
        data.put(IndicatorValue.Good, BigDecimal.ZERO);
        data.put(IndicatorValue.VeryGood, BigDecimal.ZERO);
        data.put(IndicatorValue.Excellent, BigDecimal.ZERO);
        
        List<Position> positions = positionRepository.findByAcctNumIn(accountNumbers);

        IndicatorName indicator = IndicatorName.valueOf(indicatorName);
        
        Map<IndicatorName, List<? extends BaseIndex>> indexValues = new HashMap<>();
        indexValues.put(IndicatorName.Corruption, corruptionIndexRepository.findByYear(year));
        indexValues.put(IndicatorName.Conflict, conflictIndexRepository.findByYear(year));
        indexValues.put(IndicatorName.Freedom, freedomIndexRepository.findByYear(year));
        
        for(Position pos : positions) {
        	BigDecimal posIndicator = getIndicatorForPosition(indexValues.get(indicator), pos.getCountry());
        	if(posIndicator == null) {
        		continue;
        	}
        	IndicatorValue posIndicatorValue = thresholdMappingService.mapToValue(indicator, posIndicator);
        	data.put(posIndicatorValue, data.get(posIndicatorValue).add(pos.getMktVal()));
        }

        List<IndicatorRisk> result = new ArrayList<>();
        for(Map.Entry<IndicatorValue, BigDecimal> e : data.entrySet()) {
        	
        	if(e.getValue().intValue() == 0) {
        		// @TODO: RG, remove
            	result.add(new IndicatorRisk(e.getKey(), new BigDecimal(20000000)));
        		continue;
        	}
        	
        	result.add(new IndicatorRisk(e.getKey(), e.getValue()));
        }
        
        return result;
    }

	private BigDecimal getIndicatorForPosition(List<? extends BaseIndex> list, Country country) {
		for(BaseIndex idx : list) {
			if(idx.getCountry().equals(country.getCode())) {
				return new BigDecimal(idx.getVal());
			}
		}
		return null;
	}

	

    
}
