package com.kade.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashMain9001.class, args);
    }
}
