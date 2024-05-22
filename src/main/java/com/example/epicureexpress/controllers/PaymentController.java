package com.example.epicureexpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {
    public PaymentController(){

    }

    @GetMapping("/paymentrequest")
    public String requestGet(){
        return "payment.html";
    }

    @PostMapping("/paymentconfirm")
    public String confirmPost(){
        return "redirect:/makeorder";
    }
}
