package com.mars.engine.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class FXRate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    private String currencyName;
    private double rate;
    protected FXRate() {}
    public FXRate(String cur, double r) {
        currencyName = cur;
        rate = r;
    }
}
