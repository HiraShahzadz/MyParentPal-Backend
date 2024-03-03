package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    public enum Role {
        ADMIN,
        CHILD,
        PARENT
    }
    @Autowired
    private UserRepo repo;

    public void saveorUpdate(User users) {

        repo.save(users);
    }
    public List<User> listChildAndParentUsers() {
        List<User> users = this.repo.findChildAndParentUsers();
        // Filter out admin user
        users.removeIf(user -> user.getEmail().equals("admin"));
        return users;
    }
    public Iterable<User> listAll() {

        return this.repo.findAll();
    }
    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }


    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
    public long getTotalUsersCount() {
        return repo.count();
    }

    public long getParentUsersCount() {
        return repo.countByRole("parent");
    }

    public long getChildUsersCount() {
        return repo.countByRole("child");
    }

}
