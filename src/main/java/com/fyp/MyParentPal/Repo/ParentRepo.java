package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Parent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepo extends MongoRepository<Parent,String> {
    Parent findByEmail(String email);
    boolean existsByEmail(String email);

}
