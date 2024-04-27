package com.fyp.MyParentPal.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.fyp.MyParentPal.Entity.RewardRequest;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RewardRequestRepo extends MongoRepository<RewardRequest,String> {
    List<RewardRequest> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
