package com.mars.engine.Service;

import com.mars.engine.Entity.Order;

import java.util.HashMap;
import java.util.Map;

public class tradeEngine {
    Map<String, OrderBook> orderBookMap;

    public tradeEngine(){
        orderBookMap = new HashMap<>();
    }

    public void processOrder(Order order){

    }
}
