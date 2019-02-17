package com.mars.engine.Resource;
import com.mars.engine.Dao.FXRateDao;
import com.mars.engine.Entity.FXRate;

import java.util.HashMap;
import java.util.Map;


public interface FXMgr {

    void setRate(String cur, double rate);

    boolean valid(String cur);

    double getRate(String cur);

    default double fxRate(String from, String to){
        if(from.equals(to)) return 1.0;
        else{
            double r1 = getRate(from);
            double r2 = getRate(to);
            return r1/r2;
        }
    }
}
