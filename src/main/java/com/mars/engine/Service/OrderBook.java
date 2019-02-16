package com.mars.engine.Service;

import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderBook {
    String item;
    TreeMap<Price,List<Order>>  BuySide;
    TreeMap<Price,List<Order>> SellSide;
    Price  marketPrice;

    public OrderBook(String i, Price p){
        item = i;
        marketPrice = p;
        BuySide = new TreeMap<>();
        SellSide = new TreeMap<>();
    }

    public void addOrder(Order order){
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

    public void updateBook(Order order){
        if(order.state()== Order.State.canceled || order.state()==Order.State.filled){
            Map<Price,List<Order>> sideTree;
            if(order.side()==Order.Side.BUY) sideTree = BuySide;
            else sideTree = SellSide;
            sideTree.get(order.price()).remove(order);
            //no more orders
            if(sideTree.get(order.price()).isEmpty())
                sideTree.remove(order.price());
        }
    }

    public void fillOrders(){
        if(BuySide.isEmpty()||SellSide.isEmpty()) return;
        Price bestBuyPrice = BuySide.lastKey();
        Price bestSellPrice = SellSide.firstKey();
        while(bestBuyPrice!=null&&bestSellPrice!=null&&bestBuyPrice.compareTo(bestSellPrice)>=0){
            Order buyOrder = BuySide.get(bestBuyPrice).get(0);
            Order sellOrder = SellSide.get(bestSellPrice).get(0);
            marketPrice = FillEngine.FillOrder(buyOrder,sellOrder,marketPrice);
            updateBook(buyOrder);
            updateBook(sellOrder);
            if(BuySide.isEmpty()||SellSide.isEmpty()) return;
            bestBuyPrice = BuySide.lastKey();
            bestSellPrice = SellSide.firstKey();
        }
    }


}
