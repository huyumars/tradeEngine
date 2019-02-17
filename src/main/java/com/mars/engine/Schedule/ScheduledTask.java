package com.mars.engine.Schedule;

import com.mars.engine.Service.ExecutionService;
import com.mars.engine.Service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Configurable
@EnableScheduling
public class ScheduledTask{

    @Autowired
    TradeService tradeService;

    @Autowired
    ExecutionService executionService;

    @Scheduled(fixedRate = 1000 * 1)
    public void trideTick(){
        tradeService.fillOrders();
    }

    @Scheduled(fixedRate = 1000 * 1)
    public void reportExecution(){
        executionService.saveExecutions();
    }


    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }

}
