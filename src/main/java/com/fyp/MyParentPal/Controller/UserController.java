package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Child;
import com.fyp.MyParentPal.Entity.Parent;
import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Service.AdminServices;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Add your frontend origin here
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    private AdminServices adminServices;
    @PostMapping(value = "/save-child")
    public ResponseEntity<String> saveChild(@RequestBody Child child) {
        // Check if the email already exists in the database
        if (userServices.existsByEmail(child.getEmail())) {
            return ResponseEntity.status(401).body("Email already exists");
        }

        // Save or update the child
        userServices.saveorUpdate(child);
        return ResponseEntity.ok().body(child.getId());
    }
    @PostMapping(value = "/save-parent")
    public ResponseEntity<String> saveParent(@RequestBody Parent parent) {
        // Check if the email already exists in the database
        if (userServices.existsByEmail(parent.getEmail())) {
            return ResponseEntity.status(401).body("Email already exists");
        }

        // Save or update the child
        userServices.saveorUpdate(parent);
        return ResponseEntity.ok().body(parent.getId());
    }


    @GetMapping(value = "/get-all")
    public Iterable<User> getUsers() {
        return userServices.listAll();
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User credentials) {
        try {
            String email = credentials.getEmail();
            String password = credentials.getPassword();

            User authenticatedUser = userServices.findByEmail(email);

            System.out.println(authenticatedUser);

            if (authenticatedUser != null && authenticatedUser.getPassword().equals(password)) {
                if (authenticatedUser instanceof Parent) {

                    return ResponseEntity.ok().body("{\"message\":\"Parent Login successful\"}");
                } else if (authenticatedUser instanceof Child) {
                    return ResponseEntity.ok().body("{\"message\":\"Child Login successful\"}");
                }
                else if (authenticatedUser.getEmail().equals("admin")&& authenticatedUser.getPassword().equals("Admin@123")) {
                    return ResponseEntity.ok().body("{\"message\":\"Admin Login successful\"}");
                }

            }
            return ResponseEntity.status(401).body("Wrong email or password");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred during login: " + e.getMessage());
        }
    }

}
