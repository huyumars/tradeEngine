package com.mars.engine.Resource;

import com.mars.engine.Entity.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderMgr {
    Map<String, Order>  orders;

    public OrderMgr(){
        orders = new HashMap<>();
    };

    public void addOrder(Order order){
        orders.put(order.orderID(),order);
    }

    public Order getOrder(String oid){
        return orders.get(oid);
    }

    public boolean hasOrder(String oid){ return orders.containsKey(oid);}

    public void update(String oid, Order o){ orders.put(oid,o);}


}
