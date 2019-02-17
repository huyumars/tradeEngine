package com.mars.engine;

import com.mars.engine.Entity.Impl.LimtedOrder;
import com.mars.engine.Entity.Impl.MarketOrder;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import com.mars.engine.Model.OrderBook;
import junit.framework.TestCase;
import org.junit.Test;

public class OrderBookTest {
    @Test
    public void OrderFillTest(){
        //using same currency
        OrderBook book = new OrderBook("item", new Price(100,100,"CNY"));
        Order b1 = new LimtedOrder("item", new Price(100,100,"CNY"), Order.Side.BUY, 120);
        Order b2 = new LimtedOrder("item", new Price(110,100,"CNY"), Order.Side.BUY, 10);
        Order b3 = new LimtedOrder("item", Price.Factory("0.9CNY"),Order.Side.BUY, 50);
        Order s1 = new LimtedOrder("item", new Price(100,100,"CNY"), Order.Side.SELL, 100);
        Order s2 = new MarketOrder("item",  Order.Side.SELL,  100);
        book.addOrder(b1);
        book.addOrder(b2);
        book.addOrder(s1);
        book.addOrder(s2);
        book.addOrder(b3);
        book.fillOrders();
        //sell 2 filled
        TestCase.assertTrue(s2.state()== Order.State.filled);
        //sell 1
        TestCase.assertTrue(s1.leaveQuantity()==70);
        //buy 1 filled
        //buy 2 filled
        TestCase.assertTrue(b1.state()==Order.State.filled);
        TestCase.assertTrue(b2.state()==Order.State.filled);
        //buy 3 buy nothing
        TestCase.assertTrue(b3.leaveQuantity()==b3.quantity());

        TestCase.assertTrue(book.marketPrice().equals(Price.Factory("1.0CNY")));

    }

    @Test
    public void cancelOrderTest(){
        OrderBook book = new OrderBook("item", new Price(100,100,"CNY"));
        Order b1 = new LimtedOrder("item", new Price(100,100,"CNY"), Order.Side.BUY, 120);
        Order b2 = new LimtedOrder("item", new Price(110,100,"CNY"), Order.Side.BUY, 10);
        Order b3 = new LimtedOrder("item", Price.Factory("0.9CNY"),Order.Side.BUY, 50);
        Order s1 = new LimtedOrder("item", new Price(100,100,"CNY"), Order.Side.SELL, 100);
        Order s2 = new MarketOrder("item",  Order.Side.SELL,  100);
        book.addOrder(b1);
        book.addOrder(b2);
        book.addOrder(s1);
        book.addOrder(s2);
        book.addOrder(b3);
        b2.cancel();
        book.fillOrders();
        //sell 2 filled
        TestCase.assertTrue(s2.state()== Order.State.filled);
        //sell 1
        TestCase.assertTrue(s1.leaveQuantity()==80);
        //buy 1 filled
        //buy 2 filled
        TestCase.assertTrue(b1.state()==Order.State.filled);
        TestCase.assertTrue(b2.state()==Order.State.canceled);
        //buy 3 buy nothing
        TestCase.assertTrue(b3.leaveQuantity()==b3.quantity());
    }

    @Test
    public void updateOrderTest(){
        OrderBook book = new OrderBook("item", new Price(100,100,"CNY"));
        Order b1 = new LimtedOrder("item", new Price(100,100,"CNY"), Order.Side.BUY, 120);
        Order b2 = new LimtedOrder("item", new Price(110,100,"CNY"), Order.Side.BUY, 10);
        Order b3 = new LimtedOrder("item", Price.Factory("0.9CNY"),Order.Side.BUY, 50);
        Order s1 = new LimtedOrder("item", new Price(100,100,"CNY"), Order.Side.SELL, 100);
        Order s2 = new MarketOrder("item",  Order.Side.SELL,  100);
        book.addOrder(b1);
        book.addOrder(b2);
        book.addOrder(s1);
        book.addOrder(s2);
        book.addOrder(b3);

        Order nb3 = new LimtedOrder(b3);
        nb3.updatePrice(Price.Factory("1.2CNY"));
        nb3.updateQty(500);
        book.updateOrder(b3, nb3);

        book.fillOrders();
        //sell 2 filled
        TestCase.assertTrue(s2.state()== Order.State.filled);
        //sell 1
        TestCase.assertTrue(s1.state()==Order.State.filled);
        //buy 1 nothing happen
        //buy 2 nothing happen
        TestCase.assertTrue(b1.leaveQuantity() == b1.quantity());
        TestCase.assertTrue(b2.leaveQuantity() == b2.quantity());
        //buy 3 buy nothing
        TestCase.assertTrue(nb3.leaveQuantity()==500-200);


    }
}
