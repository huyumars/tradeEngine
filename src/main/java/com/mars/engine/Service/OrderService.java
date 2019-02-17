package com.mars.engine.Service;

import com.mars.engine.Entity.Impl.LimtedOrder;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import com.mars.engine.Resource.OrderMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    TradeService tradeService;

    OrderMgr orderMgr;

    protected OrderService() {
       orderMgr  = new OrderMgr();
    }

    private boolean checkOrder(Order o){
        return true;
    }

    public boolean addOrder(Order o){
        if(checkOrder(o)){
            orderMgr.addOrder(o);
            tradeService.feedValidOrder(o);
            return true;
        }
        else return false;
    }

    public boolean updateOrder(Order o){
        if(orderMgr.hasOrder(o.orderID())){
            Order old = orderMgr.getOrder(o.orderID());
            orderMgr.update(o.orderID(),o);
            tradeService.updateOrder(old,o);
        }
        return false;
    }

    public boolean cancelOrder(String oid){
        if(orderMgr.hasOrder(oid)){
            Order o = orderMgr.getOrder(oid);
            if(o.state()== Order.State.open){
                o.cancel();
                return true;
            }
        }
        return false;
    }
}
