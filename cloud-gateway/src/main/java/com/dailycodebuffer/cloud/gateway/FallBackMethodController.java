package com.dailycodebuffer.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "User Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod() {
        return "Department Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/emailServiceFallBack")
    public String emailServiceFallBackMethod() {
        return "Email Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/grpcServiceFallBack")
    public String grpcServiceFallBackMethod() {
        return "gRPC Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/schedulerServiceFallBack")
    public String schedulerServiceFallBackMethod() {
        return "Scheduler Service is taking longer than Expected." +
                " Please try again later";
    }

}
