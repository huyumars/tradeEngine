package com.mars.engine;

import com.mars.engine.Dao.FXRateDao;
import com.mars.engine.Entity.FXRate;
import com.mars.engine.Entity.Price;
import com.mars.engine.Resource.FXMgr;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PriceTest {
    @Before
    public void addFxInfo(){
        Price.fxMgr.setRate("CNY",1.0);
        Price.fxMgr.setRate("USD",7.0);
    }

    @Test
    public void FxPriceTest(){
        Price cny = Price.Factory("7.0CNY");
        Price usd = Price.Factory("1.0USD");
        TestCase.assertTrue(cny.equals(usd));
    }

    @Test
    public void PriceTest(){
        Price cnyp1 = new Price(10,100, "CNY");
        Price cnyp2 = new Price(20,100, "CNY");
        Price usdp1 = new Price(30,100,"USD");
        Price usdp2 = new Price(5,100,"USD");

        //print
        System.out.println(cnyp1);
        //compare
        TestCase.assertTrue(cnyp1.compareTo(cnyp2)<0);
        TestCase.assertTrue(usdp1.compareTo(cnyp2)>0);

        List<Price> lp = new ArrayList<>();
        lp.add(cnyp1);
        lp.add(cnyp2);
        lp.add(usdp1);
        lp.add(usdp2);
        Collections.sort(lp);
        System.out.println("====sort=====");
        for(Price p:lp){
            System.out.println(p);
        }

        //equal
        Price cnyp12 = new Price(100,1000, "CNY");
        TestCase.assertTrue(cnyp12.equals(cnyp1));

        //test for Map
        Map<Price,Integer> map = new TreeMap<Price,Integer>();
        map.put(cnyp1,10);
        TestCase.assertTrue(map.get(cnyp12)==10);

        //test for MarketPrice
        Price marketBuy = Price.marketBuyPrice;
        Price marketSell = Price.marketSellPrice;
        TestCase.assertTrue(cnyp1.compareTo(marketBuy)<0);
        TestCase.assertTrue(usdp1.compareTo(marketSell)>0);
        TestCase.assertTrue(marketBuy.compareTo(cnyp1)>0);
        TestCase.assertTrue(marketSell.compareTo(cnyp1)<0);
        TestCase.assertTrue(marketSell.compareTo(marketSell)==0);

        //Price Factory
        TestCase.assertTrue(marketBuy == Price.Factory(marketBuy.toString()));
        TestCase.assertTrue(cnyp12.equals(Price.Factory(cnyp12.toString())));
    }
}
