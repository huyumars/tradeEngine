package com.mars.engine;

import com.mars.engine.Data.FXMgr;
import com.mars.engine.Data.Price;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EngineApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void PriceTest(){
        Price cnyp1 = new Price(10,100, FXMgr.Currency.CNY);
        Price cnyp2 = new Price(20,100, FXMgr.Currency.CNY);
        Price usdp1 = new Price(30,100,FXMgr.Currency.USD);
        Price usdp2 = new Price(5,100,FXMgr.Currency.USD);
        //print
        System.out.println(cnyp1);
        //compare
        TestCase.assertTrue(cnyp1.compareTo(cnyp2)<0);
        TestCase.assertTrue(usdp1.compareTo(cnyp2)>0);

        List<Price> lp = new ArrayList<Price>();
        lp.add(cnyp1);
        lp.add(cnyp2);
        lp.add(usdp1);
        lp.add(usdp2);
        Collections.sort(lp);
        for(Price p:lp){
            System.out.println(p);
        }

        //equal
        Price cnyp12 = new Price(100,1000, FXMgr.Currency.CNY);
        TestCase.assertTrue(cnyp12.equals(cnyp1));

        //test for Map
        Map<Price,Integer> map = new TreeMap<Price,Integer>();
        map.put(cnyp1,10);
        TestCase.assertTrue(map.get(cnyp12)==10);
    }

}

