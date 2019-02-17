package com.mars.engine.Model;

import com.mars.engine.Entity.Execution;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class FillAlgo {

    public FillAlgo(Price initPrice){
        marketPrice = initPrice;
        executions = new ArrayList<>() ;
    }

    Price  marketPrice;
    List<Execution>  executions;

    public void fillOrder(Order buy, Order sell){
        if(buy.state()== Order.State.open && sell.state() == Order.State.open) {
            int fillQty = Math.min(buy.leaveQuantity(), sell.leaveQuantity());
            buy.fill(fillQty);
            sell.fill(fillQty);
            Price filledPrice = null;
            if (buy.type() == Order.OrderType.Limited && sell.type() == Order.OrderType.Limited) {
                //buy at sell price
                filledPrice = sell.price();
            } else if (buy.type() == Order.OrderType.Limited && sell.type() == Order.OrderType.Market) {
                //sell at buy price
                filledPrice = buy.price();
            } else if (buy.type() == Order.OrderType.Market && sell.type() == Order.OrderType.Limited) {
                //buy at sell price
                filledPrice = sell.price();
            } else {
                //two market orders filled at cur market price
                filledPrice = marketPrice;
            }
            executions.add(new Execution(buy, filledPrice, fillQty));
            executions.add(new Execution(sell, filledPrice, fillQty));
            marketPrice = filledPrice;
        }
    }

    public Price getMarketPrice() {return marketPrice;}

    public List<Execution> getExecutions(){
        List<Execution> list = executions;
        executions = new ArrayList<>();
        return list;
    }
}
