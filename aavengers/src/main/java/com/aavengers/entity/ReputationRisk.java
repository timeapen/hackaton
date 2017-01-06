package com.aavengers.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ReputationIndex")
public class ReputationRisk {

    @Id
    @Column(name = "security_description", columnDefinition="CHAR(45)")
    String securityDesc;

    @Column(name = "idx_val", columnDefinition="DOUBLE")
    BigDecimal value;

    public String getSecurityDesc() {

        return securityDesc;
    }

    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
