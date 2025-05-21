package com.example.assign2.Controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.assign2.Entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MessageController {

    @RequestMapping("/hello")
    public String sayHello()
    {
        return "This is SENG2050 Assignment 2!";
    }

    @RequestMapping("/hello2")
    public Message sayHelloWorld()
    {
        return new Message("Good luck!");
    }
}
