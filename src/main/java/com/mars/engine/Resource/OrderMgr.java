package com.mars.engine.Resource;

import com.mars.engine.Entity.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderMgr {
    Map<String, Order>  orders;
    static OrderMgr mgr = new OrderMgr();
    public static OrderMgr instance(){
        return mgr;
    }

    private OrderMgr(){
        orders = new HashMap<>();
    };

    public void addOrder(Order order){
        orders.put(order.orderID(),order);
    }

    public Order getOrder(String oid){
        return orders.get(oid);
    }
}
