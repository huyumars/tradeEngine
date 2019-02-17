package com.mars.engine.Service;

import com.mars.engine.Dao.FXRateDao;
import com.mars.engine.Entity.Impl.LimtedOrder;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import com.mars.engine.Resource.FXMgr;
import com.mars.engine.Resource.OrderMgr;
import com.mars.engine.Resource.impl.FXMgrDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class OrderService {

    @Autowired
    TradeService tradeService;

    @Autowired
    FXRateDao fxRateDao;

    OrderMgr orderMgr;

    protected OrderService() {
        orderMgr  = new OrderMgr();

    }

    @PostConstruct
    public void registerCallback(){
        FXMgr mgr = new FXMgrDaoImpl(fxRateDao);
        Price.fxMgr = mgr;
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
