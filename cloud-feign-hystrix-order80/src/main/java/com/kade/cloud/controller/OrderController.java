package com.kade.cloud.controller;


import com.kade.cloud.feign.PaymentFeign;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderController {
    @Resource
    private PaymentFeign paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){

        String result = paymentHystrixService.paymentInfo_OK(id);

        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);

        return result;
    }

    public String paymentInfo_TimeoutHandler(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙，请10秒后再试，o(╥﹏╥)o";
    }

    public String payment_Global_FallbackMethod(){
        return "Global 已经宕机, please try 10 second later";
    }

}
