package com.fyp.MyParentPal.Repo;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskSubmissionRepo extends MongoRepository<TaskSubmission,String> {
    TaskSubmission findByTaskid(String taskId);
}
