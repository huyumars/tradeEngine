package com.mars.engine;

import com.mars.engine.Entity.FXRate;
import com.mars.engine.Dao.FXRateDao;
import com.mars.engine.Entity.Impl.LimtedOrder;
import com.mars.engine.Entity.Impl.MarketOrder;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import com.mars.engine.Service.OrderService;
import com.mars.engine.Service.TradeService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EngineApplicationTests {

    @Autowired
    FXRateDao fxRateDao;

    @Autowired
    OrderService orderService;

    @Autowired
    TradeService tradeService;

    private void loadFXData(){
        FXRate cnyfx = new FXRate("CNY", 1.0);
        FXRate usdfx = new FXRate("USD",7.0);
        fxRateDao.save(cnyfx);
        fxRateDao.save(usdfx);
    }

    @Before
    public void loadData(){
        loadFXData();
        symbols.add("baidu");
        symbols.add("alibaba");
        symbols.add("tecent");
    }

    @Test
    public void contextLoads() {
    }

    static ArrayList<String> symbols = new ArrayList<>();
    Order generateLimitedOrder(){
        Random random = new Random();
        String item = symbols.get(random.nextInt(symbols.size()));
        int price = random.nextInt(10)+100;
        Price p = new Price(price,100,random.nextBoolean()?"USD":"CNY");
        int qty = random.nextInt(100)+100;
        Order.Side side = random.nextBoolean()==true? Order.Side.BUY:Order.Side.SELL;
        return new LimtedOrder(item,p,side,qty);
    }

    Order generateMarketOrder(){
        Random random = new Random();
        String item = symbols.get(random.nextInt(symbols.size()));
        int price = random.nextInt(10)+100;
        int qty = random.nextInt(100)+100;
        Order.Side side = random.nextBoolean()==true? Order.Side.BUY:Order.Side.SELL;
        return new MarketOrder(item,side,qty);
    }

    void sendOrder(){
        Random r = new Random();
        int number = r.nextInt(10);
        for(int i =0; i<number;++i){
            orderService.addOrder(generateLimitedOrder());
        }
        number = r.nextInt(10);
        for(int i =0; i<number;++i){
            orderService.addOrder(generateMarketOrder());
        }
    }

    @Test
    public void simulateTradeExchange() throws InterruptedException {
        int t = 60;
        while(t>0){
            sendOrder();
            sleep(900);
            t--;
        }
    }

    @Test
    public void cancelOrderTest() throws InterruptedException {
        int t = 60;
        while(t>0){
            sendOrder();
            Order o = generateLimitedOrder();
            orderService.addOrder(o);
            sleep(900);
            if(orderService.cancelOrder(o.orderID())){
                System.out.println("canceled order");
                TestCase.assertTrue(o.state()== Order.State.canceled);
            }
            else TestCase.assertTrue(o.state()== Order.State.filled);
            t--;
        }
    }





}

