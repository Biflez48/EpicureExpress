package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Bucket;
import com.example.epicureexpress.repositories.BucketRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BucketController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final NavbarService navbarService;
    private final BucketRepository bucketRepository;

    public BucketController(
            LoggedUserManagementService loggedUserManagementService,
            NavbarService navbarService,
            BucketRepository bucketRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.navbarService = navbarService;
        this.bucketRepository = bucketRepository;
    }

    @GetMapping("/bucket")
    public String bucketGet(
            Model model
    ){
        String username = loggedUserManagementService.getUsername();
        if(username == null){
            return "redirect:/";
        }else{
            navbarService.getNavbar(model);
            model.addAttribute("authorizeForm", "logoutform");
        }

        List<Bucket> products = bucketRepository.findNomenclatures();
        model.addAttribute("productsView",products);

        return "bucket.html";
    }

    @PostMapping("/edit-bucket")
    public ResponseEntity<Map<String, Object>> editBucket(@RequestBody Map<String, Object> payload) {
        int userId = loggedUserManagementService.getId();
        int productId = (Integer) payload.get("productId");
        boolean increment = (Boolean) payload.get("increment");

        try {
            bucketRepository.updateProductCount(userId, productId, increment);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/delete-from-bucket")
    public ResponseEntity<Map<String, Object>> deleteFromBucket(@RequestBody Map<String, Object> payload) {
        int userId = loggedUserManagementService.getId();
        int productId = (Integer) payload.get("productId");

        try {
            bucketRepository.deleteProductFromBucket(userId, productId);
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
