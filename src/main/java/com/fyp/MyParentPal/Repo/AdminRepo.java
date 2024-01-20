package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends MongoRepository<Admin,String> {
    Admin findByEmail(String email);
    boolean existsByEmail(String email);
}
