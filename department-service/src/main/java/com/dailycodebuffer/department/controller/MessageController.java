package com.dailycodebuffer.department.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
public class MessageController {

//    @Value("${spring.boot.message}")
    private String message = "okoko";

    @GetMapping("message")
    public String message(){
        return message;
    }
}
