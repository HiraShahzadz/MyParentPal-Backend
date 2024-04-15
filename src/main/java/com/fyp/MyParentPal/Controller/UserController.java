package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Child;
import com.fyp.MyParentPal.Entity.Parent;
import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Service.AdminServices;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<String> saveParentGoogle(@RequestBody Map<String, String> userData) {
        String email = userData.get("email");
        String idToken = userData.get("idToken");

        // Validate email and idToken, fetch additional user info from Google if needed

        // Check if the email already exists in the database
        if (userServices.existsByEmail(email)) {
            return ResponseEntity.status(401).body("Email already exists");
        }

        // Save or update the parent user
        // You may need additional logic here to handle Google sign-in specifics
        // For example, fetching additional user info from Google and saving it along with the user data
        // userServices.saveorUpdate(parent);
System.out.println("sucessssss");
        return ResponseEntity.ok().body("User signed up successfully");
    }


    @GetMapping(value = "/get-all")
    public Iterable<User> getUsers() {
System.out.println("Report is "+userServices.listChildAndParentUsers());
        return userServices.listChildAndParentUsers();
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
    @GetMapping(value = "/count-users")
    public ResponseEntity<?> countParentChildUsers() {
        try {
            long parentUsers = userServices.getParentUsersCount();
            long childUsers = userServices.getChildUsersCount();
            long totalUsers=parentUsers+childUsers;
            System.out.println("Parent Users "+parentUsers);
            System.out.println("Child Users "+childUsers);
            System.out.println("Total Users "+totalUsers);
            return ResponseEntity.ok(new UserCountsResponse(parentUsers, childUsers,totalUsers));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
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

