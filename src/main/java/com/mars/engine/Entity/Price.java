package com.mars.engine.Entity;

import com.mars.engine.Resource.FXMgr;

public class Price implements Comparable<Price> {
    int number;
    int precision;

    public static Price marketBuyPrice = new Price(Order.Side.BUY);
    public static Price marketSellPrice = new Price(Order.Side.SELL);

    @Override
    public int compareTo(Price o) {
        //for market order price
        if(o == this) return 0;
        else if (this==marketBuyPrice||o==marketSellPrice)  return 1;
        else if (this==marketSellPrice||o==marketBuyPrice)  return -1;

        double fxr = FXMgr.instance().fxRate(this.currency,o.currency);
        double delta = ((double)number*fxr/precision - (double) o.number/o.precision);
        if(Math.abs(delta)<=0.00001) return 0;
        else if (delta>0) return 1;
        else  return -1;
    }
    String currency;

    private Price(Order.Side type){
       //build for market order price
    }

    public static Price Factory(String priceStr){
        if(priceStr.equals("marketBuyPrice")) return marketBuyPrice;
        else if(priceStr.equals("marketSellPrice")) return marketSellPrice;
        String cur = priceStr.substring(priceStr.length()-3);
        String str = priceStr.substring(0,priceStr.length()-3);
        double val = Double.valueOf(str);
        return new Price((int)(val*100), 100, cur);
    }

    public Price(int _number, int _precision, String _cur){
        number = _number;
        precision = _precision;
        currency = _cur;
    }

    @Override
    public String toString(){
        if(this==marketBuyPrice) return "marketBuyPrice";
        if(this==marketSellPrice) return "marketSellPrice";
        double price = (double) number/precision;
        return price+currency;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Price){
            return ((Price) obj).compareTo(this)==0;
        }
        return false;
    }
}
