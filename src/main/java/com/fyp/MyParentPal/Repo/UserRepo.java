package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Child;
import com.fyp.MyParentPal.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
