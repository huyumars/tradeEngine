package com.mars.engine.Dao;

import com.mars.engine.Entity.FXRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FXRateDao extends JpaRepository<FXRate,Long> {
}
