package com.fyp.MyParentPal.Service;

import com.fyp.MyParentPal.Entity.Query;
import com.fyp.MyParentPal.Repo.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServices {
    @Autowired
    private QueryRepo repo;
    public void saveorUpdate(Query queries) {

        repo.save(queries);
    }
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
    public List<Query> getAllQueries() {
        return repo.findAll();
    }

    public Query getQueryById(String id) {
        return repo.findById(id).orElse(null);
    }


}

