package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Order;
import com.example.epicureexpress.repositories.OrdersRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourierController {

    private final NavbarService navbarService;
    private final LoggedUserManagementService loggedUserManagementService;
    private final OrdersRepository ordersRepository;

    public CourierController(
            NavbarService navbarService,
            LoggedUserManagementService loggedUserManagementService,
            OrdersRepository ordersRepository
    ) {
        this.navbarService = navbarService;
        this.loggedUserManagementService = loggedUserManagementService;
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/courier")
    public String courierGet(
            Model model
    ){

        navbarService.getNavbar(model);

        String userRole = loggedUserManagementService.getRoleName();
        if(userRole == null || !userRole.equals("courier")){
            return "redirect:/";
        }

        List<Order> orders = ordersRepository.getOrdersForCourier();
        model.addAttribute("ordersView", orders);

        return "courier.html";
    }

    @PostMapping("/nextstatus")
    public String changeStatusPost(
            @RequestParam int idorder
    ){
        ordersRepository.changeOrderStatus(idorder);
        return "redirect:/courier";
    }
}
