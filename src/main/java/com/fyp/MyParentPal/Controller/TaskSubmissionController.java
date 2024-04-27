package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.RewardRequest;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import com.fyp.MyParentPal.Service.TaskSubmissionServices;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/task_submission")
public class TaskSubmissionController {
    @Autowired
    private TaskSubmissionServices Services;

    @GetMapping(value = "/getall")
    public Iterable<TaskSubmission> getSubmitFile() {
        return Services.listAll();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            // Get file bytes
            byte[] fileBytes = file.getBytes();

            // Get file name
            String fileName = file.getOriginalFilename();

            // Get file type
            String fileType = file.getContentType();

            // Create TaskSubmission object
            TaskSubmission submission = new TaskSubmission();
            submission.setFileName(fileName);
            submission.setFileType(fileType);
            submission.setFiles(fileBytes);

            // Save submission to the database
            Services.save(submission);

            return ResponseEntity.ok().body("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error uploading file.");
        }
    }



}
