package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Query;
import com.fyp.MyParentPal.Service.QueryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Add your frontend origin here
@RequestMapping("api/v1/user")
public class QueryController {
    @Autowired
    private QueryServices queryServices;

    @PostMapping(value = "/save-query")
    public ResponseEntity<String> saveQuery(@RequestBody Query query) {
        // Check if the email already exists in the database
        if (queryServices.existsByEmail(query.getEmail())) {
            return ResponseEntity.status(401).body("Email already exists");
        }
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Karachi"));
        query.setDate(localDate);
        System.out.println("Date isss: "+localDate);
        queryServices.saveorUpdate(query);
        return ResponseEntity.ok().body(query.getId());
    }

    @PutMapping(value = "/update-query-status/{queryId}")
    public ResponseEntity<String> updateQueryStatus(@PathVariable String queryId) {
        Query query = queryServices.getQueryById(queryId);
        if (query == null) {
            return ResponseEntity.status(404).body("Query not found");
        }

        // Update status to "responded"
        query.setStatus("responded");
        queryServices.saveorUpdate(query);

        // Check if the status was updated in the database
        Query updatedQuery = queryServices.getQueryById(queryId);
        if (updatedQuery != null && "responded".equals(updatedQuery.getStatus())) {
            return ResponseEntity.ok().body("Query status updated to responded");
        } else {
            return ResponseEntity.status(500).body("Failed to update query status");
        }
    }

    @GetMapping(value = "/get-all-queries")
    public List<Query> getAllQueries() {
        System.out.println("Queries are "+queryServices.getAllQueries());
        return queryServices.getAllQueries();
    }
    public QueryController(QueryServices queryServices) {
        this.queryServices = queryServices;
    }

}
