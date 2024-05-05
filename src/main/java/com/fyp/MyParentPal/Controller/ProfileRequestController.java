package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.ProfileRequest;
import com.fyp.MyParentPal.Entity.Task;
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
    private ProfileRequestServices Services;
    @Autowired
    private Task mytask;
    @PostMapping(value = "/save")
    public ResponseEntity<ProfileRequest> EditDetails(@RequestBody ProfileRequest profile) {

        if (mytask == null || mytask.getChildId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // Fetch child ID from mytask
        String Id = mytask.getChildId();
        System.out.println("Child id in profile: " + Id);
        profile.setStatus("Pending");
        byte[] decodedImage = Base64.getDecoder().decode(profile.getImg());

        profile.setImage(decodedImage);
        profile.setChildId(Id);
        Services.save(profile);
        //System.out.println("Updated Profile: " +profile.toString());
        return ResponseEntity.ok().build();
    }
}
