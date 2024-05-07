package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.DeadlineExtension;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import com.fyp.MyParentPal.Repo.DeadlineExtensionRepo;
import com.fyp.MyParentPal.Repo.TaskSubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeadlineExtensionServices {
    @Autowired
    private DeadlineExtensionRepo repo;

    public void save(DeadlineExtension extension) {
        repo.save(extension);
    }

    public Iterable<DeadlineExtension> listAll() {

        return this.repo.findAll();
    }
}
