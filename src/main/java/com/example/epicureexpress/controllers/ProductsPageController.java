package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductsPageController {
    private final LoggedUserManagementService loggedUserManagementService;
    private final CategoriesRepository categoriesRepository;
    private final NomenclaturesRepository nomenclaturesRepository;

    public ProductsPageController(
            LoggedUserManagementService loggedUserManagementService,
            CategoriesRepository categoriesRepository,
            NomenclaturesRepository nomenclaturesRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.categoriesRepository = categoriesRepository;
        this.nomenclaturesRepository = nomenclaturesRepository;
    }

    @GetMapping("/products")
    public String productsGet(
            @RequestParam(required = false) String selectedtype,
            @RequestParam(required = false) String selectedcategory,
            Model model
    ){
        List<Category> categories = categoriesRepository.findAllCategories();
        model.addAttribute("categories", categories);

        String username = loggedUserManagementService.getUsername();
        if(username == null){
            model.addAttribute("authorizeForm", "loginbth");
        }else{
            model.addAttribute("authorizeForm", "logoutform");
        }

        List<Nomenclature> nomenclatures = nomenclaturesRepository.findNomenclature();
        model.addAttribute("productsView", nomenclatures);

        return "products.html";
    }
}
