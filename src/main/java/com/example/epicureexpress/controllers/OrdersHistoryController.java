package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Order;
import com.example.epicureexpress.repositories.OrdersRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrdersHistoryController {

    private final OrdersRepository ordersRepository;

    private final LoggedUserManagementService loggedUserManagementService;

    private final NavbarService navbarService;

    public OrdersHistoryController(
            NavbarService navbarService,
            LoggedUserManagementService loggedUserManagementService,
            OrdersRepository ordersRepository
    ){
        this.navbarService = navbarService;
        this.loggedUserManagementService = loggedUserManagementService;
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/orders")
    public String historyGet(
            Model model
    ){
        int userid = loggedUserManagementService.getId();
        String username = loggedUserManagementService.getUsername();
        if(username == null){
            return "redirect:/";
        }else{
            navbarService.getNavbar(model);
            model.addAttribute("authorizeForm", "logoutform");
        }

        List<Order> orders = ordersRepository.getOrdersByUserId(userid);
        model.addAttribute("ordersView", orders);

        return "history.html";
    }
}
