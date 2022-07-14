package alibaba.cloud.feign;

import com.kade.cloud.entities.CommonResult;
import com.kade.cloud.entities.Payment;
import org.springframework.stereotype.Component;


@Component
public class PaymentFallback implements PaymentService {

    //如果nacos-payment-consumer服务中的相应接口出事了，我来兜底
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {

        return new CommonResult<>(4444444,"服务降级返回---PaymentFallbackService", new Payment(id, "errorSerial...."));
    }
}

