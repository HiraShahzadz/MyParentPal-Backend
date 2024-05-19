package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.ProfileRequest;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.*;
import com.fyp.MyParentPal.Service.ProfileRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/profile")
public class ProfileRequestController {
    @Autowired
    private ProfileRequestServices profileRequestServices;
    @Autowired
    private Task mytask;
    @PostMapping(value = "/save")
    public ResponseEntity<ProfileRequest> editDetails(@RequestBody ProfileRequest profile) {
        if (mytask == null || mytask.getChildId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Fetch child ID from mytask
        String Id =  mytask.getChildId();
        System.out.println("Child id in profile: " + Id);

        // Check if there is a pending request for this child ID
        if (isPendingRequest(Id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Or you can choose another appropriate HTTP status code
        }

        profile.setStatus("Pending");

        // Check if the parent with the provided ID exists
        byte[] decodedImage = Base64.getDecoder().decode(profile.getImg());
        profile.setImage(decodedImage);
        profile.setChildId(Id);
        profileRequestServices.save(profile);
        //System.out.println("Updated Profile: " +profile.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getall")
    public Iterable<ProfileRequest> getUsers() {
        return profileRequestServices.listAll();
    }

    @PutMapping(value = "/edit-profile-req/{id}")
    private ProfileRequest updateStatus(@RequestBody ProfileRequest updatedProfile, @PathVariable(name = "id") String _id) {
        ProfileRequest existingProfile = profileRequestServices.getProfileByID(_id);

        if (existingProfile != null) {
            existingProfile.setStatus(updatedProfile.getStatus());
            profileRequestServices.save(existingProfile);
            System.out.println(existingProfile);
            return existingProfile;
        } else {
            // Handle the case when the task with the given ID is not found
            // You might want to return an appropriate response or throw an exception
            return null;
        }
    }
    private boolean isPendingRequest(String childId) {
        return profileRequestServices.isRequestPending(childId);
    }
}

