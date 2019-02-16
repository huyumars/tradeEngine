package com.mars.engine.Dao;

import com.mars.engine.Entity.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionDao extends JpaRepository<Execution,Long> {
}
