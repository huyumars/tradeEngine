package com.mars.engine.DataImpl;

import com.mars.engine.Data.Order;
import com.mars.engine.Data.Price;

import java.util.UUID;

public class OrderImpl implements Order {
    String orderID;
    String item;
    Price price;
    Side side;
    OrderType type;
    int quantity;
    int filled;

    public OrderImpl(String i, Price p, Side s,OrderType t,int q){
        orderID = UUID.randomUUID().toString();
        item = i;
        price = p;
        side = s;
        type = t;
        quantity = q;
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
           return true;
       }
    }

    @Override
    public String toString(){
        return "[ Order:"+orderID()+" ("+ side().toString()+") "+ " has "+quantity()+" filled "+filled();
    }
}
