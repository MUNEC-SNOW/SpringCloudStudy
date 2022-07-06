package com.kade.cloud.feign;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrix implements PaymentFeign{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentHystrix ok fallback";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentHystrix timeout fallback";
    }
}
