package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Child;
import com.fyp.MyParentPal.Entity.Parent;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Service.AdminServices;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Add your frontend origin here
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    private AdminServices adminServices;
    @Autowired
    private Child mychild;
    @Autowired
    private Task task;
    String childId;
    @PostMapping(value = "/save-child")
    public ResponseEntity<String> saveChild(@RequestBody Child child) {
        // Convert Base64 string to byte array
        System.out.println("Parent id at child saving time:" + child.getParentId());
        // Convert Base64 string to byte array
        String imgBase64 = child.getImg();
        if (imgBase64 != null) {
            byte[] decodedImage = Base64.getDecoder().decode(child.getImg());
            child.setImage(decodedImage);
        }
        child.setParentId(mychild.getParentId());
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

    @GetMapping(value = "/get-parent")
    public ResponseEntity<List<Parent>> getParentData() {
        try {
            // Get the parent ID from mychild
            String parentId = mychild.getParentId();


            // Fetch All User Data
            Iterable<User> userData = userServices.listAll();

            // Filter out only Parent objects with matching parent ID
            List<Parent> parents = new ArrayList<>();
            for (User user : userData) {
                if (user instanceof Parent) {
                    Parent parent = (Parent) user;
                    String userId = parent.getId();
                    if (userId != null && userId.equals(parentId)) {
                        parents.add(parent);
                    }
                }
            }

            return ResponseEntity.ok().body(parents);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @GetMapping(value = "/get-child")
    public ResponseEntity<List<Child>> getChildData() {
        try {
            // Get the parent ID from mychild
            String parentId = mychild.getParentId();

            // Fetch All User Data
            Iterable<User> childData = userServices.listAll();

            // Filter out only Child objects with matching parent ID
            List<Child> children = new ArrayList<>();
            for (User user : childData) {
                if (user instanceof Child) {
                    Child child = (Child) user;
                    String childParentId = child.getParentId();
                    if (childParentId != null && childParentId.equals(parentId)) {
                        children.add(child);
                    }
                }
            }

            return ResponseEntity.ok().body(children);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User credentials) {
        try {
            String email = credentials.getEmail();
            String password = credentials.getPassword();

            User authenticatedUser = userServices.findByEmail(email);



            if (authenticatedUser != null && authenticatedUser.getPassword().equals(password)) {
                if (authenticatedUser instanceof Parent) {
                    String parentId = authenticatedUser.getId();
                    System.out.println("Parent id:" + parentId);
                    mychild.setParentId(parentId);
                    System.out.println("Parent id after setting:" + mychild.getParentId());
                    return ResponseEntity.ok().body("{\"message\":\"Parent Login successful\"}");
                } else if (authenticatedUser instanceof Child) {
                    childId =  authenticatedUser.getId();
                    System.out.println("Child id:" + childId);
                    task.setChildId(childId);
                    System.out.println("Child id after setting:" + task.getChildId());
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
