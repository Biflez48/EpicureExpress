package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.repositories.BucketRepository;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductsPageController {
    private final NomenclaturesRepository nomenclaturesRepository;
    private final NavbarService navbarService;
    private final LoggedUserManagementService loggedUserManagementService;
    private final BucketRepository bucketRepository;

    public ProductsPageController(
            NomenclaturesRepository nomenclaturesRepository,
            NavbarService navbarService,
            LoggedUserManagementService loggedUserManagementService,
            BucketRepository bucketRepository
    ){
        this.nomenclaturesRepository = nomenclaturesRepository;
        this.navbarService = navbarService;
        this.loggedUserManagementService = loggedUserManagementService;
        this.bucketRepository = bucketRepository;
    }

    @GetMapping("/products")
    public String productsGet(
            @RequestParam(required = false) String selectedtype,
            @RequestParam(required = false) String selectedcategory,
            Model model
    ){
        navbarService.getNavbar(model);
        if(selectedtype==null && selectedcategory==null){
            return "redirect:/";
        }

        String userRole = loggedUserManagementService.getRoleName();
        if(userRole != null && userRole.equals("courier")){
            return "redirect:/courier";
        }

        List<Nomenclature> nomenclatures = nomenclaturesRepository.findNomenclature(selectedtype,selectedcategory);
        model.addAttribute("productsView", nomenclatures);

        int userIdRole = loggedUserManagementService.getIdRole();
        if(userIdRole == 1){
            model.addAttribute("typebutton", "deleteprodbtn");
        }else{
            model.addAttribute("typebutton", "addtobucketbtn");
        }

        return "products.html";
    }

    @PostMapping("/add-to-bucket")
    public ResponseEntity<Map<String, Object>> addToBucket(@RequestBody Map<String, Object> payload) {
        String username = loggedUserManagementService.getUsername();
        if(username == null){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        int productId = (Integer) payload.get("productId");

        try {
            bucketRepository.addToBucket(productId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/delete-prod")
    public ResponseEntity<Map<String, Object>> deleteProduct(@RequestBody Map<String, Object> payload) {
        int idRole = loggedUserManagementService.getIdRole();;
        if(idRole != 1){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        int productId = (Integer) payload.get("productId");

        try {
            nomenclaturesRepository.deleteNomenclatureById(productId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
