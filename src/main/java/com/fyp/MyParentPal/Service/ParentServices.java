package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Parent;
import com.fyp.MyParentPal.Repo.ParentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentServices {

    @Autowired
    private ParentRepo repo;

    public void saveorUpdate(Parent parents) {

        repo.save(parents);
    }
    public Iterable<Parent> listAll() {

        return this.repo.findAll();
    }
    public Parent findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

}
