package com.kade.cloud.controller;

import com.kade.cloud.service.PaymentService;
import com.kade.cloud.entities.CommonResult;
import com.kade.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    private Integer ServerPort;

    @PostMapping(value = "payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("insert :{}",result);

        if (result > 0) {
            return new CommonResult(200, "ok", ServerPort);
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
