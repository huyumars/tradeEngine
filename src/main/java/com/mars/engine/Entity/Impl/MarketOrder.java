package com.mars.engine.Entity.Impl;

import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;

public class MarketOrder extends OrderImpl {
    public MarketOrder(String i, Side s, int q) {
        super(i, null, s, OrderType.Market, q);
        if(s==Side.BUY) price = Price.marketBuyPrice;
        else price = Price.marketSellPrice;
    }

    public MarketOrder(Order other){
        super(other);
    }
}
