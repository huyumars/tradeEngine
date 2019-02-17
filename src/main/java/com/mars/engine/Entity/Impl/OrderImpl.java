package com.mars.engine.Entity.Impl;

import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import com.mars.engine.Resource.OrderMgr;

import java.util.HashMap;
import java.util.Map;

public class OrderImpl implements Order {
    String orderID;
    String item;
    Price price;
    Side side;
    State state;
    OrderType type;
    int quantity;
    int filled;

    static Map<String,Integer> IdTable = new HashMap<String,Integer>();
    static synchronized String orderIDgenerator(String item){
        if(IdTable.containsKey(item))
            IdTable.put(item,IdTable.get(item)+1);
        else IdTable.put(item,0);
        return item+IdTable.get(item);
    }

    public OrderImpl(Order other){
        orderID = other.orderID();
        item = other.item();
        price = other.price();
        side = other.side();
        type = other.type();
        quantity = other.quantity();
        state = other.state();
    }

    public OrderImpl(String i, Price p, Side s,OrderType t,int q){
        orderID = orderIDgenerator(i);
        item = i;
        price = p;
        side = s;
        type = t;
        quantity = q;
        state = State.open;
    }

    @Override
    public String item() {
        return item;
    }

    @Override
    public String orderID() {
        return orderID;
    }

    @Override
    public Price price() {
        return price;
    }

    @Override
    public Side side() {
        return side;
    }

    @Override
    public OrderType type() {
        return type;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    public int filled() {
        return filled;
    }

    @Override
    public boolean fill(int quantity) {
       if(quantity>leaveQuantity()) return false;
       else {
           filled += quantity;
           if(leaveQuantity()==0){
               state = State.filled;
           }
           return true;
       }
    }

    @Override
    public void updatePrice(Price newPrice) {
        // avoid change marketPrice
        if(price==Price.marketSellPrice||price==Price.marketBuyPrice) return;
        if(newPrice==Price.marketSellPrice||newPrice==Price.marketBuyPrice) return;
        price = newPrice;
    }

    @Override
    public void updateQty(int quantity) {
        if(quantity<leaveQuantity()) return;
        this.quantity = quantity;
    }

    @Override
    public void cancel() {
        state = State.canceled;
    }

    @Override
    public String toString(){
        return "[ Order:"+orderID()+" ("+ side().toString()+") "+ " has "+quantity()+" filled "+filled();
    }

}
