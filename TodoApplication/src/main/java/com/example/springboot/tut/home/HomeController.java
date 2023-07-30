package com.example.springboot.tut.home;

import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String greeting(){
        return "Hello to spring boot";
    }

    @GetMapping(value = "/{name}")
    public String greetingName(@PathVariable String name){
        return format("Hello %s to spring boot",name);
    }
}
