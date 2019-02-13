package com.mars.engine.Data;

import com.mars.engine.Data.FXMgr.Currency;

public class Price implements Comparable<Price> {
    int number;
    int precision;

    @Override
    public int compareTo(Price o) {
        double fxr = FXMgr.fxRate(this.currency,o.currency);
        double delta = ((double)number*fxr/precision - (double) o.number/o.precision);
        if(Math.abs(delta)<=0.00001) return 0;
        else if (delta>0) return 1;
        else  return -1;
    }
    Currency currency;

    public Price(int _number, int _precision, Currency _cur){
        number = _number;
        precision = _precision;
        currency = _cur;
    }

    @Override
    public String toString(){
        double price = (double) number/precision;
        return String.valueOf(price)+FXMgr.currencyString(currency);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Price){
            return ((Price) obj).compareTo(this)==0;
        }
        return false;
    }
}
