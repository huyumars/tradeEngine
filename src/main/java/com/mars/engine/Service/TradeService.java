package com.mars.engine.Service;

import com.mars.engine.Entity.Execution;
import com.mars.engine.Entity.Order;
import com.mars.engine.Model.TradeEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TradeService {

    TradeEngine tradeEngine;

    @Autowired
    ExecutionService executionService;

    TradeService(){
        tradeEngine = new TradeEngine();
    }

    void feedValidOrder(Order order){
        tradeEngine.feedOrder(order);
    }

    void updateOrder(Order order, Order newOrder){
        tradeEngine.updateOrder(order,newOrder);
    }

    public void fillOrders(){
        System.out.println("============");
        List<Execution> executions = new ArrayList<>();
        for(String item: tradeEngine.tradingItems()){
            List<Execution> e = tradeEngine.fillOrders(item);
            if(e!=null) executions.addAll(e);
            System.out.println(item + " price is "+ tradeEngine.marketPrice(item));
        }
        executionService.feedExecutions(executions);



    }
}
