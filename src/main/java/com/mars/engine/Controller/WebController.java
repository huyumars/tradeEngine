package com.mars.engine.Controller;

import com.mars.engine.Dao.UserDao;
import com.mars.engine.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebController {

    @Autowired
    private UserDao rep;

    @RequestMapping("/hello")
    public String helloWorld(){
        System.out.println("here !");
        User t = new User("yuhu","mars");
        rep.save(t);

        List<User> l = rep.findByFirstName("yuhu");
        for (User u: l){
            System.out.println(u.toString());
        }
        return "helloWorld";
    }
}
