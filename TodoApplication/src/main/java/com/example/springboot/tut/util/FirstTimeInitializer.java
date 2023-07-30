package com.example.springboot.tut.util;

import com.example.springboot.tut.security.AppUser;
import com.example.springboot.tut.security.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInitializer implements CommandLineRunner {
    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
        if(userService.findAll().isEmpty()){
            logger.info("there's no users ,Lets create one!");

            AppUser user = new AppUser("Salah","salah@example.com","123456");
            userService.save(user);
        }
    }
}
