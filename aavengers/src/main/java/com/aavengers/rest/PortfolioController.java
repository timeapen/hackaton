package com.aavengers.rest;

import com.aavengers.CorruptionIndex;
import com.aavengers.OverallRisk;
import com.aavengers.Position;
import com.aavengers.data.CorruptionIndexRepository;
import com.aavengers.data.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    CorruptionIndexRepository corruptionIndexRepository;

    @ResponseBody
    @GetMapping(value = "/positions/{accountNumber}", produces = "application/json")
    public List<Position> getIndicators(@PathVariable String accountNumber) {
        return positionRepository.findByAcctNum(accountNumber);
    }

    @ResponseBody
    @GetMapping(value = "/overallRisk/{accountNumber}", produces = "application/json")
    public OverallRisk getOverallRisk(@PathVariable String accountNumber) {

        Map<String, String> data = new HashMap<>();
        Map<String, BigDecimal> corruptionIdxMap = new HashMap<>();
        List<Position> positions = positionRepository.findByAcctNum(accountNumber);
        List<CorruptionIndex> corruptionIdx = corruptionIndexRepository.findByYear(2016);
        BigDecimal calculatedTotal = BigDecimal.ZERO;
        BigDecimal totalMarketValue = BigDecimal.ZERO;


        for(CorruptionIndex idx : corruptionIdx) {
            corruptionIdxMap.put(idx.getCountry(), new BigDecimal(idx.getVal()));
        }

        for(Position pos : positions) {
            BigDecimal posCorruptionIdx = corruptionIdxMap.get(pos.getCountry().getCode());
            calculatedTotal = calculatedTotal.add(posCorruptionIdx.multiply(pos.getMktVal()));
            totalMarketValue = totalMarketValue.add(pos.getMktVal());
        }

        BigDecimal corruptionIdxValue = calculatedTotal.divide(totalMarketValue);


        return new OverallRisk(data);
    }
}
