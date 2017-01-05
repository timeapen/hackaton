package com.aavengers;


import javax.persistence.*;

@Entity
@Table(name = "CORRUPTION_INDEX")
public class CorruptionIndex {

    @Id @GeneratedValue
    Long id;

    @Column(name = "country_code", columnDefinition="CHAR(3)")
    String country;


    @Column(name = "idx_year")
    int year;

    @Column(name = "idx_val", columnDefinition="DOUBLE")
    float val;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }
}
