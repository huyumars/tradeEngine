package com.mars.engine;


import com.mars.engine.Dao.ExecutionDao;
import com.mars.engine.Entity.Execution;
import com.mars.engine.Entity.Impl.MarketOrder;
import com.mars.engine.Entity.Order;
import com.mars.engine.Entity.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Autowired
    ExecutionDao executionDao;

    @Test
    public void ExecutionDaoTest(){
        Order o = new MarketOrder("item", Order.Side.SELL, 100);
        Execution exe = new Execution(o, Price.Factory("1CNY"), 100);
        executionDao.save(exe);
        List<Execution> le = executionDao.findAll();
        for(Execution e: le){
            System.out.println(e);
        }
    }
}
