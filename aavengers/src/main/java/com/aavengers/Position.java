package com.aavengers;


import javax.persistence.*;

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
}
