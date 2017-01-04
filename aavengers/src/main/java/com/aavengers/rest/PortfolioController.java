package com.aavengers.rest;

import com.aavengers.Position;
import com.aavengers.data.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    PositionRepository positionRepository;

    @ResponseBody
    @GetMapping(value = "/positions/{accountNumber}", produces = "application/json")
    public List<Position> getIndicators(@PathVariable String accountNumber) {

        return positionRepository.findByAcctNum(accountNumber);
    }
}
