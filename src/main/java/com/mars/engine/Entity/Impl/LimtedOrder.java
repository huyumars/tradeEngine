package com.mars.engine.Entity.Impl;

import com.mars.engine.Entity.Price;

public class LimtedOrder extends OrderImpl {
    public LimtedOrder(String i, Price p, Side s, int q) {
        super(i, p, s, OrderType.Limited, q);
    }
}
