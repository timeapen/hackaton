package com.aavengers.data;

import com.aavengers.entity.FreedomIndex;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreedomIndexRepository extends CrudRepository<FreedomIndex, Long> {
    List<FreedomIndex> findByYear(int year);
}