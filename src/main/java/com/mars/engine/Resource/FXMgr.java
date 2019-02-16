package com.mars.engine.Resource;
import com.mars.engine.Dao.FXRateDao;

import java.util.HashMap;
import java.util.Map;


public class FXMgr {
    Map<String,Double> rates;

    static FXMgr mgr = new FXMgr();

    static public FXMgr instance(){
        return mgr;
    }

    private FXMgr(){
        rates = new HashMap<>();
    }

    public void setRate(String cur, double rate){
        rates.put(cur,rate);
    }

    public double fxRate(String from, String to){
        if(from.equals(to)) return 1.0;
        else{
            double r1 = rates.get(from);
            double r2 = rates.get(to);
            return r1/r2;
        }
    }
}
