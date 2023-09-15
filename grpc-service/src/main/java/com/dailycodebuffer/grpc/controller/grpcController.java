package com.dailycodebuffer.grpc.controller;

import com.dailycodebuffer.grpc.service.BookAuthorServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grpc")
@Slf4j
public class grpcController {

    @PostMapping(value = "/send")
    public String sendGrpc() {
        return "okokokok";
    }
}
