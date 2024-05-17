package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductsPageController {
    private final NomenclaturesRepository nomenclaturesRepository;
    private final NavbarService navbarService;

    public ProductsPageController(
            NomenclaturesRepository nomenclaturesRepository,
            NavbarService navbarService
    ){
        this.nomenclaturesRepository = nomenclaturesRepository;
        this.navbarService = navbarService;
    }

    @GetMapping("/products")
    public String productsGet(
            @RequestParam(required = false) String logsuccess,
            @RequestParam(required = false) String registersuccess,
            @RequestParam(required = false) String selectedtype,
            @RequestParam(required = false) String selectedcategory,
            Model model
    ){
        navbarService.getNavbar(model,"/products",logsuccess,registersuccess);

        if(selectedtype==null && selectedcategory==null){
            return "redirect:/";
        }

        List<Nomenclature> nomenclatures = nomenclaturesRepository.findNomenclature(selectedtype,selectedcategory);
        model.addAttribute("productsView", nomenclatures);

        return "products.html";
    }
}
