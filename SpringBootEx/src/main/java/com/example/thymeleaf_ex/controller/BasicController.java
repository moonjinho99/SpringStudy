package com.example.thymeleaf_ex.controller;



import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {

    @GetMapping("/test-basic")
    public String textBasic(Model model){
        model.addAttribute("data","<b>Hello Spring<b>");
        return "basic/test-basic";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA",10);
        User userB = new User("userB",20);

        List<User> list = new ArrayList<>(Arrays.asList(userA,userB));

        Map<String ,User> map = new HashMap<>();
        map.put("userA",userA);
        map.put("userB",userB);

        model.addAttribute("user",userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap",map);

        return "basic/variable";
    }

    

    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age){
            this.username = username;
            this.age=age;
        }
    }
}



