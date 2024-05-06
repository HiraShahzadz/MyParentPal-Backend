package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FeedbackRepo extends MongoRepository<Feedback,String> {
}