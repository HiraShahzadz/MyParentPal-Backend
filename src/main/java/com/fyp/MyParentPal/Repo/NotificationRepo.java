package com.fyp.MyParentPal.Repo;

import com.fyp.MyParentPal.Entity.Notification;
import com.fyp.MyParentPal.Entity.RewardRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificationRepo  extends MongoRepository<Notification,String> {

}
