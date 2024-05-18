package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;

@Controller
public class AdministrationController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final NomenclaturesRepository nomenclaturesRepository;
    private final NavbarService navbarService;

    public AdministrationController(
            LoggedUserManagementService loggedUserManagementService,
            NomenclaturesRepository nomenclaturesRepository,
            NavbarService navbarService
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.nomenclaturesRepository = nomenclaturesRepository;
        this.navbarService = navbarService;
    }

    @GetMapping("/administrate")
    public String administrateGet(
            Model model
    ){
        int userRole = loggedUserManagementService.getIdRole();

        if(userRole != 1){
            return "redirect:/";
        }else{
            navbarService.getNavbar(model,"/bucket");
            model.addAttribute("authorizeForm", "logoutform");
        }

        return "administration.html";
    }

    @PostMapping("/uploadimage")
    public String uploadImagePost(
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam String price
    ) throws IOException {

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setImage(file.getBytes());
        nomenclature.setName(name);
        nomenclature.setPrice(new BigDecimal(price));
        nomenclature.setCountPurchase(0);
        nomenclature.setIdType(1);

        nomenclaturesRepository.addNomenclature(nomenclature);

        return "redirect:/administrate";
    }
}
