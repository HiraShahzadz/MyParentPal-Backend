package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Child;
import com.fyp.MyParentPal.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    User findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("{ 'role' : { $ne: 'admin' } }")
    List<User> findChildAndParentUsers();

    long countByRole(String role);
}
