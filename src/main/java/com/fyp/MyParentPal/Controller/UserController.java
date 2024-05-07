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
        byte[] decodedImage = Base64.getDecoder().decode(child.getImg());
        child.setImage(decodedImage);
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

    @PutMapping(value = "/editParent/{id}")
    public ResponseEntity<Parent> updateParentDetails(@RequestBody Parent updatedParent, @PathVariable(name = "id") String parentId) {
        try {
            // Fetch the parent from the database based on the provided ID
            Parent existingParent = (Parent) userServices.getUserByID(parentId);

            // Check if the parent with the provided ID exists
            if (existingParent == null) {
                return ResponseEntity.notFound().build();
            }

            // Update the existing parent's details with the provided data
            existingParent.setEmail(updatedParent.getEmail());
            existingParent.setPassword(updatedParent.getPassword());
            existingParent.setFirstName(updatedParent.getFirstName());
            existingParent.setLastName(updatedParent.getLastName());
            existingParent.setPhoneNo(updatedParent.getPhoneNo());
            existingParent.setCnic(updatedParent.getCnic());
            // Convert Base64 string to byte array


            String imgBase64 = updatedParent.getImg();
            if (imgBase64 != null) {
                byte[] decodedImage = Base64.getDecoder().decode(updatedParent.getImg());
                existingParent.setImage(decodedImage);
                existingParent.setImg(updatedParent.getImg());
            }

            // Similarly, update other attributes as needed

            // Save the updated parent back to the database
            userServices.saveorUpdate(existingParent);

            // Return the updated parent with a success status
            return ResponseEntity.ok(existingParent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    @PutMapping(value = "/editChild/{id}")
    public ResponseEntity<Child> updateChildDetails(@RequestBody Child updatedChild, @PathVariable(name = "id") String childId) {
        try {
            // Fetch the child from the database based on the provided ID
            Child existingChild = (Child) userServices.getUserByID(childId);

            // Check if the child with the provided ID exists
            if (existingChild == null) {
                return ResponseEntity.notFound().build();
            }

            // Update the existing child's details with the provided data
            existingChild.setEmail(updatedChild.getEmail());
            existingChild.setPassword(updatedChild.getPassword());
            existingChild.setName(updatedChild.getName());
            existingChild.setTags(updatedChild.getTags());
            existingChild.setDob(updatedChild.getDob());
            existingChild.setGender(updatedChild.getGender());

            // Convert Base64 string to byte array
            String imgBase64 = updatedChild.getImg();
            if (imgBase64 != null) {
                byte[] decodedImage = Base64.getDecoder().decode(updatedChild.getImg());
                existingChild.setImage(decodedImage);
                existingChild.setImg(updatedChild.getImg());
            }

            // Save the updated parent back to the database
            userServices.saveorUpdate(existingChild);

            // Return the updated parent with a success status
            return ResponseEntity.ok(existingChild);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping(value = "/getChildId")
    public ResponseEntity<List<Child>> getChildIdData() {
        try {
            // Fetch All User Data
            Iterable<User> userData = userServices.listAll();

            // Filter out only Parent objects with matching parent ID
            List<Child> listChild = new ArrayList<>();
            for (User user : userData) {
                if (user instanceof Child) {
                    Child child = (Child) user;
                    String userId = child.getId();
                    if (userId != null && userId.equals(childId)) {
                        listChild.add(child);
                    }
                }
            }

            return ResponseEntity.ok().body(listChild);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


}

