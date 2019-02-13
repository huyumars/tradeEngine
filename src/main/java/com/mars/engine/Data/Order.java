package com.mars.engine.Data;

public interface Order {
    enum Side{
        BUY,
        SELL
    }
    enum OrderType {
        Market,
        Limited
    }
    String orderID();
    Price price ();
    Side  side();
    OrderType type();
}
