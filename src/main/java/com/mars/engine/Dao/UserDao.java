package com.mars.engine.Dao;

import com.mars.engine.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {

    List<User> findByLastName(String lastName);
    List<User> findByFirstName(String firstName);
}
