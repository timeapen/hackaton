package com.aavengers.data;

import com.aavengers.entity.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionRepository extends CrudRepository<Position, Long> {

    List<Position> findByAcctNum(String acctNum);
    
    List<Position> findByAcctNumIn(String[] acctNum);
}