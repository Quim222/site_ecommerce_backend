package com.eCommerce.shoppingSite.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.shoppingSite.services.PaymentService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/payments")
public class PaymentControler {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestParam Long amount) throws StripeException {
        return paymentService.createPaymentIntent(amount);
    }

}
