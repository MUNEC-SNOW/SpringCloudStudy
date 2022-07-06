package com.kade.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {
    public String paymentOk(Integer id) {
        return "线程池：  "+ Thread.currentThread().getName()+"   paymentInfo_OK,  id:  "+id
                +"\t"+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_timeout(Integer id) {
        int age = 10/0;

        return "线程池：  "+ Thread.currentThread().getName()+"   paymentInfo_Timeout,  id:  "+id
                +"\t"+"╭(╯^╰)╮";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号： "+serialNumber;
    }

    //服务降级方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试， o(╥﹏╥)o  id: "+id;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池：  "+ Thread.currentThread().getName()+"  系统繁忙请稍后再试,  id:  "+id
                +"\t"+"o(╥﹏╥)o";
//        return "线程池：  "+ Thread.currentThread().getName()+"   paymentInfo_TimeoutHandler,  id:  "+id
//                +"\t"+"o(╥﹏╥)o";
    }
}
