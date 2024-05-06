package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Feedback;
import com.fyp.MyParentPal.Entity.Notification;
import com.fyp.MyParentPal.Entity.RewardRequest;
import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Repo.NotificationRepo;
import com.fyp.MyParentPal.Service.NotificationServices;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/messages")
public class NotificationController {

    @Autowired
    private NotificationServices messageService;
    @Autowired
    private UserServices userServices;
    // Fetch All User Data

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody RewardRequest rewardRequest) {
        // Extract taskname and other details from the RewardRequest object
        //getting name of currently login child

        String taskname = rewardRequest.getTaskname();

        // Hardcoded message with taskname
        String message = " Your child "+ "Sent Request for a task " + taskname ; // Example hardcoded message with taskname


        Long toId = 123L; // Example recipient ID

        // Create a Notification object with the recipient ID and message
        Notification notification = new Notification();
        notification.setToId(toId);
        notification.setMessage(message);

        // Save the notification details in the database
        messageService.save(notification);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getall")
    public Iterable<Notification> getTasks() {
        return messageService.listAll();
    }


}