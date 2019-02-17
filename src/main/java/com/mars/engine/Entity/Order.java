package com.mars.engine.Entity;

public interface Order extends Cloneable{
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
        filled,
        canceled,
    }
    String item();
    String orderID();
    Price price ();
    Side  side();
    OrderType type();
    State state();
    int quantity();
    int filled();
    boolean fill(int quantity);

    //modify order
    void updatePrice(Price newPrice);
    void updateQty(int quantity);
    void cancel();

    default int leaveQuantity() {
        return quantity()-filled();
    }
}
