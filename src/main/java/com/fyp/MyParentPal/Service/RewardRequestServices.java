package com.fyp.MyParentPal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.MyParentPal.Entity.RewardRequest;
import com.fyp.MyParentPal.Repo.RewardRequestRepo;


@Service
public class RewardRequestServices {

    @Autowired
    private RewardRequestRepo repo;

    public void save(RewardRequest rewardrequest) {

        repo.save(rewardrequest);
    }

    public Iterable<RewardRequest> listAll() {

        return this.repo.findAll();
    }

    public RewardRequest getTaskByID(String requestId) {

        return repo.findById(requestId).get();
    }
}
