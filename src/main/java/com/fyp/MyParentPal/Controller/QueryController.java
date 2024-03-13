package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.EmailDTO;
import com.fyp.MyParentPal.Entity.Query;
import com.fyp.MyParentPal.Service.QueryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Karachi"));
        query.setDate(localDate);
        System.out.println("Date isss: "+localDate);
        queryServices.saveorUpdate(query);
        return ResponseEntity.ok().body(query.getId());
    }

    @PutMapping("/update-query-status/{queryId}")
    public ResponseEntity<String> updateQueryStatus(
            @PathVariable String queryId,
            @RequestBody String responseText) {

        Query query = queryServices.getQueryById(queryId);
        if (query == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query not found");
        }

        // Update status to "responded"
        query.setStatus("responded");
        query.setResponse(responseText); // Set the response text
        queryServices.saveorUpdate(query);

        // Check if the status and response were updated in the database
        Query updatedQuery = queryServices.getQueryById(queryId);
        if (updatedQuery != null && "responded".equals(updatedQuery.getStatus()) &&
                responseText.equals(updatedQuery.getResponse())) {
            return ResponseEntity.ok("Query status and response updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update query status and response");
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

    @PostMapping(value = "/send-mail")
    public ResponseEntity<String> sendTriggerMail(@RequestBody EmailDTO emailDTO) {
        try {
            queryServices.sendSimpleEmail(emailDTO.getToEmail(), emailDTO.getSubject(), emailDTO.getBody());
            return ResponseEntity.ok().body("Mail trigger request accepted");
        } catch (Exception e) {
            // Handle exception
            return ResponseEntity.status(500).body("Failed to trigger mail");
        }
    }


    // Other methods remain unchanged
}


