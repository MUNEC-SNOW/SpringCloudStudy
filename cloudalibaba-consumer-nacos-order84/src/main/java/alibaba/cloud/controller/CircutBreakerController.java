package alibaba.cloud.controller;

import alibaba.cloud.feign.PaymentService;
import com.kade.cloud.entities.CommonResult;
import com.kade.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircutBreakerController {
    @Resource
    PaymentService paymentService;

    @GetMapping("/consumer/fallback/{id}")
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        return paymentService.paymentSQL(id);
    }

}
