package alibaba.cloud.feign;

import com.kade.cloud.entities.CommonResult;
import com.kade.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallback.class)
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}") //去找nacos-payment-consumer服务中的相应接口
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}

