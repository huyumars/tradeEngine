package com.mars.engine.Resource.impl;

import com.mars.engine.Dao.FXRateDao;
import com.mars.engine.Entity.FXRate;
import com.mars.engine.Resource.FXMgr;

public class FXMgrDaoImpl implements FXMgr {
    FXRateDao fxRateDao;

    public FXMgrDaoImpl(FXRateDao _fxRateDao){
        fxRateDao = _fxRateDao;
    }

    @Override
    public void setRate(String cur, double rate){
        fxRateDao.save(new FXRate(cur,rate));
    }

    @Override
    public boolean valid(String cur){
        return fxRateDao.findById(cur).isPresent();
    }

    @Override
    public double getRate(String cur){
        return fxRateDao.findById(cur).get().rate();
    }


}
