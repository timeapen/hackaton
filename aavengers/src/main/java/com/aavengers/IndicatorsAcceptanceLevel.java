package com.aavengers;

import java.util.Map;

public class IndicatorsAcceptanceLevel {
    Map<String, Integer> levels;

    public IndicatorsAcceptanceLevel() {
    }

    public IndicatorsAcceptanceLevel(Map<String, Integer> levelss) {
        this.levels = levelss;
    }

    public Map<String, Integer> getLevels() {
        return levels;
    }

    public void setLevels(Map<String, Integer> levels) {
        this.levels = levels;
    }
}
