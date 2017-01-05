package com.aavengers.data;

import com.aavengers.entity.ConflictIndex;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConflictIndexRepository extends CrudRepository<ConflictIndex, Long> {
    List<ConflictIndex> findByYear(int year);
}