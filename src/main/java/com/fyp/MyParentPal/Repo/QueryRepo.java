package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepo extends MongoRepository<Query,String> {
    boolean existsByEmail(String email);
    boolean getQueryById(String id);
    List<Query> findByStatus(String status);
}
