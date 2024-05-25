package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Bucket;
import com.example.epicureexpress.repositories.BucketRepository;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.repositories.OrdersRepository;
import com.example.epicureexpress.repositories.ProdsOrderRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    private final OrdersRepository ordersRepository;
    private final BucketRepository bucketRepository;
    private final NomenclaturesRepository nomenclaturesRepository;
    private final ProdsOrderRepository prodsOrderRepository;
    private final LoggedUserManagementService loggedUserManagementService;

    public OrderController(
            OrdersRepository ordersRepository,
            BucketRepository bucketRepository,
            NomenclaturesRepository nomenclaturesRepository,
            ProdsOrderRepository prodsOrderRepository,
            LoggedUserManagementService loggedUserManagementService
    ){
        this.ordersRepository = ordersRepository;
        this.bucketRepository = bucketRepository;
        this.nomenclaturesRepository = nomenclaturesRepository;
        this.prodsOrderRepository = prodsOrderRepository;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @PostMapping("/orderconfirm")
    public String confirmPost(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String payment
    ){

        return "redirect:/paymentrequest";
    }

    @GetMapping("/makeorder")
    public String makeOrderGet(){

        List<Bucket> products = bucketRepository.findNomenclatures();
        int idOrder = ordersRepository.AddOrder(products);

        nomenclaturesRepository.increaseCountPurchase(products);
        prodsOrderRepository.addProducts(idOrder,products);

        int idUser = loggedUserManagementService.getId();
        bucketRepository.deleteProductsByUserId(idUser);

        return "redirect:/bucket";
    }
}
