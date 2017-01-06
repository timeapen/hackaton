package com.aavengers.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IndicatorSettings")
public class IndicatorSettings {

    @Id
    @Column(name = "indicator_name", nullable = false, columnDefinition="VARCHAR(64)")
    String indicatorName;

    @Column(name = "indicator_value", nullable = false, columnDefinition="VARCHAR(64)")
    String indicatorValue;

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public String getIndicatorValue() {
		return indicatorValue;
	}

	public void setIndicatorValue(String indicatorValue) {
		this.indicatorValue = indicatorValue;
	}

}
