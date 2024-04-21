package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Child;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepo repo;

    public void saveorUpdate(User users) {
        repo.save(users);
    }

    public Iterable<User> listAll() {

        return this.repo.findAll();
    }
    public User getUserByID(String userid) {

        return repo.findById(userid).get();
    }
    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }
    

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

}
