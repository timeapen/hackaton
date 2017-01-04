package com.aavengers;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ACHS_App")
public class Position {

    @Id @GeneratedValue
    Long id;

    @Column(name = "Bank_Cd_KEY", columnDefinition="CHAR(8)")
    String bankCd;

    @Column(name = "Acct_Num_KEY", columnDefinition="CHAR(35)")
    String acctNum;

    @Column(name = "Loc_Sec_Num_KEY", columnDefinition="CHAR(12)")
    String locSecNum;

    @Column(name = "settled_acct_ccy_mkt_val", columnDefinition="DOUBLE")
    BigDecimal mktVal;

    @Column(name = "settled_marketval_acctccy", columnDefinition="CHAR(3)")
    String mktValCcy;

    //@Column(name = "Cntry_Cd_Rpt", columnDefinition="CHAR(3)")
    //String country;

    @ManyToOne(optional=false)
    @JoinColumn(name="Cntry_Cd_Rpt",referencedColumnName="code")
    Country country;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCd() {
        return bankCd;
    }

    public void setBankCd(String bankCd) {
        this.bankCd = bankCd;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public String getLocSecNum() {
        return locSecNum;
    }

    public void setLocSecNum(String locSecNum) {
        this.locSecNum = locSecNum;
    }

    public BigDecimal getMktVal() {
        return mktVal;
    }

    public void setMktVal(BigDecimal mktVal) {
        this.mktVal = mktVal;
    }

    public String getMktValCcy() {
        return mktValCcy;
    }

    public void setMktValCcy(String mktValCcy) {
        this.mktValCcy = mktValCcy;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
