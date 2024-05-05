package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.ProfileRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRequestRepo extends MongoRepository<ProfileRequest,String>{
}
