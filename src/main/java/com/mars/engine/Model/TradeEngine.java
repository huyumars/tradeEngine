package com.mars.engine.Model;

import com.mars.engine.Entity.Execution;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import com.mars.engine.Model.OrderBook;

import java.util.*;

public class TradeEngine {
    Map<String, OrderBook> orderBookMap;

    public TradeEngine(){
        orderBookMap = new HashMap<>();
    }

    public void feedOrder(Order order){
        String item = order.item();
        OrderBook orderbook=null;
        if(orderBookMap.containsKey(item)){
            orderbook = orderBookMap.get(item);
        }
        //start price at 10CNY
        else{
            orderbook = new OrderBook(item, Price.Factory("10CNY"));
            orderBookMap.put(item,orderbook);
        }
        orderbook.addOrder(order);
    }

    public void updateOrder(Order order, Order newOrder){
        String item = order.item();
        OrderBook orderbook=null;
        if(orderBookMap.containsKey(item)) {
            orderbook = orderBookMap.get(item);
            orderbook.updateOrder(order,newOrder);
        }
    }

    public List<Execution> fillOrders(String item){
        if(orderBookMap.containsKey(item)){
            return orderBookMap.get(item).fillOrders();
        }
        return null;
    }

    public Set<String> tradingItems(){
        return orderBookMap.keySet();
    }

    public String marketPrice(String item){
        return orderBookMap.get(item).marketPrice().toString();
    }
}
