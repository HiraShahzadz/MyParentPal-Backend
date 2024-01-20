package com.fyp.MyParentPal.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.fyp.MyParentPal.Entity.Task;

@Repository
public interface TaskRepo extends MongoRepository<Task,String> {
	
}
