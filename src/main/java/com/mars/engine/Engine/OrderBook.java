package com.mars.engine.Engine;

import com.mars.engine.Data.Order;
import com.mars.engine.Data.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class OrderBook {
    TreeMap<Price,List<Order>>  BuySide;
    TreeMap<Price,List<Order>> SellSide;

    public OrderBook(){
        BuySide = new TreeMap<Price,List<Order>>();
        SellSide = new TreeMap<Price,List<Order>>();
    }

    public void addLimitedOrder(Order order){
        Map<Price,List<Order>> orderSide;
        if(order.side()==Order.Side.BUY) orderSide = BuySide;
        else orderSide = SellSide;
        List<Order>  orderQueue = null;
        if(orderSide.containsKey(order.price())){
            orderQueue = orderSide.get(order.price());
        }
        else{
            orderQueue = new ArrayList<>();
            orderSide.put(order.price(),orderQueue);
        }
        orderQueue.add(order);
    }

    private int fillOrderAndLeft(Order order, int quantity){
        if(order.leaveQuantity()>=quantity){
            order.fill(quantity);
            return 0;
        }
        else{
            int left = quantity - order.leaveQuantity();
            order.fill(order.leaveQuantity());
            return left;
        }
    }

    public void fillOrders(){
        Price bestBuyPrice = BuySide.lastKey();
        Price bestSellPrice = SellSide.firstKey();
        if(bestBuyPrice.compareTo(bestSellPrice)>=0){
            //filled
            List<Order> bestSellOrders = SellSide.get(bestSellPrice);
            List<Order> bestBuyOrders = BuySide.get(bestBuyPrice);
            for(Order sorder: bestSellOrders){
                int quantity = sorder.leaveQuantity();
                while(quantity>0 && !bestBuyOrders.isEmpty()){
                    for(Order border:bestBuyOrders){
                        quantity = fillOrderAndLeft(border,quantity);
                    }

                }
            }
            fillOrders();
        }
    }


}
