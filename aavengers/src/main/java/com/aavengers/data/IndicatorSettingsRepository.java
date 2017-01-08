package com.aavengers.data;

import com.aavengers.entity.IndicatorSettings;

import org.springframework.data.repository.CrudRepository;

public interface IndicatorSettingsRepository extends CrudRepository<IndicatorSettings, String> {
	IndicatorSettings findByIndicatorName(String indicatorName);
}
