package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.User;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fyp.MyParentPal.Entity.RewardRequest;
import com.fyp.MyParentPal.Service.RewardRequestServices;
import com.fyp.MyParentPal.Repo.RewardRequestRepo; // Import the repository class
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/Reward_Request")
public class RewardRequestController {

    private final RewardRequestServices rewardRequestServices; // Define final for constructor injection
    private final RewardRequestRepo rewardRequestRepo; // Define final for constructor injection
    @Autowired
    private Task mytask;
    @Autowired
    public RewardRequestController(RewardRequestServices rewardRequestServices, RewardRequestRepo rewardRequestRepo) {
        this.rewardRequestServices = rewardRequestServices;
        this.rewardRequestRepo = rewardRequestRepo;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<List<RewardRequest>> saveRewardRequest(@RequestBody RewardRequest rewardRequest) {
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Karachi"));

        if (mytask == null || mytask.getChildId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Fetch child ID from mytask
        String childId = mytask.getChildId();
        System.out.println("Child id in rewardrequest: " + childId);
        // Set the date in the query
        rewardRequest.setDate(localDate);
        rewardRequest.setChildId(childId);
        // Set the status to "In Progress"
        rewardRequest.setStatus("In Progress");

        rewardRequestServices.save(rewardRequest);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @GetMapping(value = "/getall")
    public Iterable<RewardRequest> getRewardRequests(@RequestParam(required = false) String filter) {
        if ("latest".equals(filter)) {
            // Calculate start and end dates for the past 7 days
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(7);

            // Fetch requests for the past 7 days using findByDateBetween
            List<RewardRequest> latestRequests = rewardRequestRepo.findByDateBetween(startDate, endDate);

            // Return the latest requests
            return latestRequests;
        } else {
            // Fetch all requests
            return rewardRequestServices.listAll();
        }
    }
}
