package com.example.assign2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @RequestMapping("/")
    public @ResponseBody String greetings()
    {
        return "Seng2050 Assignment 2";
    } 

}
