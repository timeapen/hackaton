package com.aavengers;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @Column(name = "code", columnDefinition="CHAR(3)")
    String code;

    @Column(name = "name")
    String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
