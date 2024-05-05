package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Child;
import com.fyp.MyParentPal.Entity.ProfileRequest;
import com.fyp.MyParentPal.Repo.ProfileRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileRequestServices {
    @Autowired
    private ProfileRequestRepo repo;

    public void save(ProfileRequest request) {
        repo.save(request);
    }

}
