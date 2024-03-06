package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepo extends MongoRepository<Query,String> {
    boolean existsByEmail(String email);
    boolean getQueryById(String id);
}
