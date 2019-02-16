package com.mars.engine.Service;

import com.mars.engine.Entity.Execution;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;

public class FillEngine {
    static Price FillOrder(Order buy, Order sell, Price MarketPrice){
        int fillQty = Math.min(buy.leaveQuantity(),sell.leaveQuantity());
        buy.fill(fillQty);
        sell.fill(fillQty);
        Price filledPrice = null;
        if(buy.type()==Order.OrderType.Limited&&sell.type()==Order.OrderType.Limited){
            //buy at sell price
            filledPrice = sell.price();
        }
        else if(buy.type()==Order.OrderType.Limited&&sell.type()==Order.OrderType.Market){
            //sell at buy price
            filledPrice = buy.price();
        }
        else if(buy.type()==Order.OrderType.Market&&sell.type()==Order.OrderType.Limited){
            //buy at sell price
            filledPrice = sell.price();
        }
        else{
            //two market orders filled at cur market price
            filledPrice = MarketPrice;
        }
        Execution bexecution = new Execution(buy,filledPrice,fillQty);
        Execution sexecution = new Execution(sell,filledPrice,fillQty);
        System.out.println(bexecution);
        System.out.println(sexecution);
        return filledPrice;
    }
}
