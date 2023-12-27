package com.fyp.MyParentPal.Controller;
import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Add your frontend origin here
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserServices parentServices;

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveParent(@RequestBody User parents) {
        // Check if the email already exists in the database
        if (parentServices.existsByEmail(parents.getEmail())) {
            return ResponseEntity.status(401).body("Email already exists");
        }

        // Save or update the parent
        parentServices.saveorUpdate(parents);
        return ResponseEntity.ok().body(parents.get_id());
    }

    @GetMapping(value = "/getall")
    public Iterable<User> getParents() {
        return parentServices.listAll();
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User credentials) {
        try {
            String email = credentials.getEmail();
            String password = credentials.getPassword();

            User authenticatedParent = parentServices.findByEmail(email);

            System.out.println(authenticatedParent);
            if (authenticatedParent != null && authenticatedParent.getPassword().equals(password) && authenticatedParent.getRole() != null && authenticatedParent.getRole().equals("parent")) {


                return ResponseEntity.ok().body("{\"message\":\"Parent Login successful\"}");
           } else if(authenticatedParent != null && authenticatedParent.getPassword().equals(password) && authenticatedParent.getRole() != null && authenticatedParent.getRole().equals("child")){

             return ResponseEntity.ok().body("{\"message\":\"Child Login successful\"}");
            }
            else {
                return ResponseEntity.status(401).body("Wrong email or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred during login: " + e.getMessage());
        }
    }


}
