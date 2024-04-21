package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Feedback;
import com.fyp.MyParentPal.Repo.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServices {
    @Autowired
    private FeedbackRepo repo;

    public void saveorUpdate(Feedback feedback) {
        repo.save(feedback);
    }

    public Iterable<Feedback> listAll() {

        return this.repo.findAll();
    }

    public Feedback getFeedbackByID(String feedbackId) {

        return repo.findById(feedbackId).get();
    }
}
