package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.ProfileRequest;
import com.fyp.MyParentPal.Repo.ProfileRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileRequestServices {
    @Autowired
    private ProfileRequestRepo repo;

    public void save(ProfileRequest request) {
        repo.save(request);
    }
    public Iterable<ProfileRequest> listAll() {

        return this.repo.findAll();
    }
    public ProfileRequest getProfileByID(String profileId) {

        return repo.findById(profileId).get();
    }
}
