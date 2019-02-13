package com.mars.engine.Engine;

import com.mars.engine.Data.Order;
import com.mars.engine.Data.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderBook {
    Map<Price,List<Order>>  BuySide;
    Map<Price,List<Order>>  SellSide;

    public void addLimitedOrder(Order order){
        Map<Price,List<Order>> orderSide;
        if(order.side()==Order.Side.BUY) orderSide = BuySide;
        else orderSide = SellSide;
        List<Order>  orderQueue = null;
        if(orderSide.containsKey(order.price())){
            orderQueue = orderSide.get(order.price());
        }
        else{
            orderSide.put(order.price(),new ArrayList<>());
        }
        orderQueue.add(order);
    }
}
