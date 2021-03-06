package com.mars.engine.Entity;

import com.mars.engine.Resource.OrderMgr;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Execution {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    String orderId;
    Order.Side  side;
    String filledPriceString;
    int filledQuantiy;
    Date  filledTime;

    protected Execution() {}

    public Execution(Order o, Price p, int q){
        filledQuantiy = q;
        filledTime = new Date();
        orderId = o.orderID();
        filledPriceString = p.toString();
        side = o.side();
    }


    public Order.Side side() {
        return side;
    }


    public Price filledPrice() {
        return Price.Factory(filledPriceString);
    }


    public int filledQuantity() {
        return filledQuantiy;
    }

    public Date time() {
        return filledTime;
    }

    public String toString(){
        return orderId+" "+side()+" "+filledQuantity() +" at Price "+ filledPriceString;
    }

}
