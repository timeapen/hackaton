package com.aavengers.data;

import com.aavengers.entity.CorruptionIndex;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CorruptionIndexRepository extends CrudRepository<CorruptionIndex, Long> {
    List<CorruptionIndex> findByYear(int year);
}