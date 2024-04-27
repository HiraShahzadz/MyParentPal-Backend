package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import com.fyp.MyParentPal.Repo.TaskRepo;
import com.fyp.MyParentPal.Repo.TaskSubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskSubmissionServices {
    @Autowired
    private TaskSubmissionRepo repo;

    public void save(TaskSubmission submission) {
        repo.save(submission);
    }

    public Iterable<TaskSubmission> listAll() {

        return this.repo.findAll();
    }



}
