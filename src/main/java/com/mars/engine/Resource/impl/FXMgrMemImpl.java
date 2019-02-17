package com.mars.engine.Resource.impl;

import com.mars.engine.Resource.FXMgr;

import java.util.HashMap;
import java.util.Map;

public class FXMgrMemImpl implements FXMgr {
    Map<String,Double> rateMap;

    public FXMgrMemImpl(){
        rateMap = new HashMap<>();
    }

    @Override
    public void setRate(String cur, double rate) {
        rateMap.put(cur,rate);
    }

    @Override
    public boolean valid(String cur) {
        return rateMap.containsKey(cur);
    }

    @Override
    public double getRate(String cur) {
        return rateMap.get(cur);
    }
}
