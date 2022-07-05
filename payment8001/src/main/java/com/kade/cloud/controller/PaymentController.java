package com.kade.cloud.controller;

import com.kade.cloud.entities.CommonResult;
import com.kade.cloud.entities.Payment;
import com.kade.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author kade
 * @date 2022/07/04
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String ServerPost;

    @Resource
    protected DiscoveryClient discoveryClient;

    @GetMapping(value = "payment/lb")
    public String getPaymentLB(){
        return ServerPost;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ServerPost;
    }


    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("ser:{}",service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        return this.discoveryClient;
    }

    @PostMapping(value = "payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("insert :{}",result);

        if (result > 0) {
            return new CommonResult(200, "ok", result + ServerPost);
        } else {
            return new CommonResult(444, "insert fail", null);
        }
    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment res = paymentService.getPaymentById(id);
        log.info("get:{}",res);
        if (res != null) {
            return new CommonResult(200, "ok", res);
        } else {
            return new CommonResult(444, "no such payment", null);
        }
    }
}
