package com.example.assign2.Controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.assign2.Entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MessageController {

    @RequestMapping("/hello")
    public String sayHello()
    {
        return "Welcome!";
    }

    @RequestMapping("/hello2")
    public Message sayHelloWorld()
    {
        return new Message("Hello World!");
    }
}
