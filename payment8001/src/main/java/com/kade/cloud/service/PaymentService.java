package com.kade.cloud.service;

import com.kade.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author kade
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
