package com.mars.engine.Service;

import com.mars.engine.Dao.ExecutionDao;
import com.mars.engine.Entity.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExecutionService {
    @Autowired
    ExecutionDao executionDao;

    List<Execution>  executions;

    protected ExecutionService(){
        executions = new ArrayList<>();
    }

    public void feedExecutions(List<Execution> exes){
        System.out.println("----executions------");
        for(Execution e: exes)
            System.out.println(e);
        System.out.println(" ");
        executions.addAll(exes);
    }

    public void saveExecutions(){
        executionDao.saveAll(executions);
        executions.clear();
    }
}
