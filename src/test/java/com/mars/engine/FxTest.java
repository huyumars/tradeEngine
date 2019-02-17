package com.mars.engine;

import com.mars.engine.Dao.FXRateDao;
import com.mars.engine.Entity.FXRate;
import com.mars.engine.Entity.Price;
import com.mars.engine.Service.OrderService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FxTest {
    @Autowired
    FXRateDao fxRateDao;

    @Before
    public void addFxInfo(){
        Price.fxMgr.setRate("CNY",1.0);
        Price.fxMgr.setRate("USD",7.0);
    }

    @Test
    public void fxRateDaoTest(){
        //fx data from db
        TestCase.assertTrue(fxRateDao.findById("CNY").get().rate()==1.0);
        TestCase.assertTrue(fxRateDao.findById("USD").get().rate()==7.0);
    }

    @Test
    public void FxPriceTest(){
        Price cny = Price.Factory("7.0CNY");
        Price usd = Price.Factory("1.0USD");
        TestCase.assertTrue(cny.equals(usd));
    }


}
