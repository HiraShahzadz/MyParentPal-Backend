package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.DeadlineExtension;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadlineExtensionRepo extends MongoRepository<DeadlineExtension,String> {
}
