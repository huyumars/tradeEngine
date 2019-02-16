package com.mars.engine.Resource;

public class ExecutionMgr {
    static ExecutionMgr mgr = new ExecutionMgr();
    static ExecutionMgr instance(){
        return mgr;
    }
    private ExecutionMgr(){}


}
