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
    @PostMapping(value = "/save-parent-google")
    public ResponseEntity<Object> saveParentGoogle(@RequestBody Parent parent) {
        // Check if the email already exists in the database
        if (userServices.existsByEmail(parent.getEmail())) {
            // If email already exists, retrieve the existing parent and return its ID
            User existingParent = userServices.findByEmail(parent.getEmail());
            System.out.println(existingParent.getId());
            return ResponseEntity.ok().body(existingParent.getId());
        }

        // Save the new parent
        userServices.saveorUpdate(parent);
        return ResponseEntity.ok().body(parent.getId());
    }
    @GetMapping(value = "/get-all")
    public Iterable<User> getUsers() {

        return userServices.listChildAndParentUsers();
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User credentials) {
        try {
            String email = credentials.getEmail();
            String password = credentials.getPassword();

            User authenticatedUser = userServices.findByEmail(email);



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
    @PostMapping("/getId")
    public ResponseEntity<Object> getObjectId(@RequestBody User credentials) {

        String email = credentials.getEmail();
        String password = credentials.getPassword();
        User authenticatedUser = userServices.findByEmail(email);
        if (authenticatedUser != null && authenticatedUser.getPassword().equals(password)) {

            return ResponseEntity.ok().body(authenticatedUser.getId());
        }
        return null;
    }
    @GetMapping(value = "/count-users")
    public ResponseEntity<?> countParentChildUsers() {
        try {
            long parentUsers = userServices.getParentUsersCount();
            long childUsers = userServices.getChildUsersCount();
            long totalUsers=parentUsers+childUsers;

            return ResponseEntity.ok(new UserCountsResponse(parentUsers, childUsers,totalUsers));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/child-age")
    public ResponseEntity<?> getChildAge(@RequestBody User credentials) {
        String email = credentials.getEmail();

        User user = userServices.findByEmail(email);


        // Check if the user exists and is a child
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else if (!(user instanceof Child)) {
            return ResponseEntity.status(400).body("User is not a child");
        }

        // Cast the user to Child and calculate the age
        Child child = (Child) user;
        int age = child.calculateAge();
        return ResponseEntity.ok(age);
    }

    static class UserCountsResponse {
        private long parentUsers;
        private long childUsers;
        private long totalUsers;

        public long getTotalUsers() {
            return totalUsers;
        }

        public void setTotalUsers(long totalUsers) {
            this.totalUsers = totalUsers;
        }

        public long getParentUsers() {
            return parentUsers;
        }

        public void setParentUsers(long parentUsers) {
            this.parentUsers = parentUsers;
        }

        public long getChildUsers() {
            return childUsers;
        }

        public void setChildUsers(long childUsers) {
            this.childUsers = childUsers;
        }

        public UserCountsResponse(long parentUsers, long childUsers, long totalUsers) {
            this.parentUsers = parentUsers;
            this.childUsers = childUsers;
            this.totalUsers = totalUsers;
        }
    }

}

