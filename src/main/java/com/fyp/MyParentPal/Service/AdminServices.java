package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Admin;
import com.fyp.MyParentPal.Repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {
    @Autowired
    private AdminRepo repo;

    public void saveorUpdate(Admin admin) {

        repo.save(admin);
    }

    public Iterable<Admin> listAll() {

        return this.repo.findAll();
    }
    public Admin findByEmail(String email) {
        return repo.findByEmail(email);
    }


    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
}
