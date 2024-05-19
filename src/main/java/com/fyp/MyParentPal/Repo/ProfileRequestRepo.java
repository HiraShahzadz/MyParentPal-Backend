package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.ProfileRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRequestRepo extends MongoRepository<ProfileRequest,String>{
    List<ProfileRequest> findByChildIdAndStatus(String childId, String status);
}
