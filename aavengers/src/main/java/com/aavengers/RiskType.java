package com.aavengers;

public enum RiskType {
	
	RISK("RISK"), NEAR("NEAR"), SAFE("SAFE");

	private String riskType;

	RiskType(String riskType) {
		this.riskType = riskType;
	}

	String getRiskType() {
		return riskType;
	}
	
}
