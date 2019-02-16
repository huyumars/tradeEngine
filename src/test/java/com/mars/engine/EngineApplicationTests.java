package com.mars.engine;

import com.mars.engine.Entity.FXRate;
import com.mars.engine.Dao.FXRateDao;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import com.mars.engine.Entity.Impl.LimtedOrder;
import com.mars.engine.Entity.Impl.MarketOrder;
import com.mars.engine.Service.OrderBook;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EngineApplicationTests {

    @Autowired
    FXRateDao fxRateDao;

    private void loadFXData(){
        FXRate cnyfx = new FXRate("CNY", 1.0);
        FXRate usdfx = new FXRate("USD",7.0);
        fxRateDao.save(cnyfx);
        fxRateDao.save(usdfx);
    }

    @Before
    public void loadData(){
        loadFXData();
    }

    @Test
    public void contextLoads() {
    }





}

