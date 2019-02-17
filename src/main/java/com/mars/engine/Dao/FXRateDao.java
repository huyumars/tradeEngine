package com.mars.engine.Dao;

import com.mars.engine.Entity.FXRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FXRateDao extends JpaRepository<FXRate,String> {
}
