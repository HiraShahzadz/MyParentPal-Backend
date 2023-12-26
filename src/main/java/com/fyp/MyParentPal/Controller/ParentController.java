package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Parent;
import com.fyp.MyParentPal.Service.ParentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Add your frontend origin here
@RequestMapping("api/v1/parent")
public class ParentController {

    @Autowired
    private ParentServices parentServices;

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveParent(@RequestBody Parent parents) {
        // Check if the email already exists in the database
        if (parentServices.existsByEmail(parents.getEmail())) {
            return ResponseEntity.status(401).body("Email already exists");
        }

        // Save or update the parent
        parentServices.saveorUpdate(parents);
        return ResponseEntity.ok().body(parents.get_id());
    }

    @GetMapping(value = "/getall")
    public Iterable<Parent> getParents() {
        return parentServices.listAll();
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody Parent credentials) {
        try {
            String email = credentials.getEmail();
            String password = credentials.getPassword();

            // Perform authentication logic by checking against the database
            Parent authenticatedParent = parentServices.findByEmail(email);

            if (authenticatedParent != null && authenticatedParent.getPassword().equals(password)) {

                // Generate a token (example key, you should use a secure key in production)
                return ResponseEntity.ok().body("{\"message\":\"Login successful\"}");
            } else {
                return ResponseEntity.status(401).body("Wrong email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred during login: " + e.getMessage());
        }
    }


}
