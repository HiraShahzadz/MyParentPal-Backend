package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Admin;
import com.fyp.MyParentPal.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Add your frontend origin here
@RequestMapping("api/v1/user")
public class AdminController {
    @Autowired
    private AdminServices adminServices;
    @PostMapping(value = "/save-admin")
    public ResponseEntity<String> saveAdmin(@RequestBody Admin admin) {
        // Check if the email already exists in the database
        if (adminServices.existsByEmail(admin.getEmail())) {
            return ResponseEntity.status(401).body("Email already exists");
        }

        // Save or update the child
        adminServices.saveorUpdate(admin);
        return ResponseEntity.ok().body(admin.getId());
    }

}
