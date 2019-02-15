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
    enum State {
        open,
        filling,
        filled
    }
    String item();
    String orderID();
    Price price ();
    Side  side();
    OrderType type();
    int quantity();
    int filled();
    boolean fill(int quantity);
    default int leaveQuantity() {
        return quantity()-filled();
    }
}
