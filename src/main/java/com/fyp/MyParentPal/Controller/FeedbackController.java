package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Feedback;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Service.FeedbackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackServices feedbackServices;
    @PostMapping(value = "/save")
    public String saveFeedback(@RequestBody Feedback feedback) {
        feedbackServices.saveorUpdate(feedback);
        return feedback.getId();
    }
    @GetMapping(value = "/getall")
    public Iterable<Feedback> getTasks() {
        return feedbackServices.listAll();
    }

}

