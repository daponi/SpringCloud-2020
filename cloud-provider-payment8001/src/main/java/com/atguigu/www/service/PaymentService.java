package com.atguigu.www.service;

import com.atguigu.www.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById (@Param("id")Long id);
}
