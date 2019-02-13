package com.mars.engine.Data;


public class FXMgr {
    public enum Currency{
        CNY,
        USD;
    };

    static double fxRate(Currency from, Currency to){
        if(from==to){
            return 1.0;
        }
        if(from==Currency.USD) return 7.0;
        else return 1/7.0;
    }

    static String currencyString(Currency cur){
        switch (cur){
            case CNY:
                return "CNY";
            case USD:
                return "USD";
        }
        return "UNKNOWN";
    }
}
