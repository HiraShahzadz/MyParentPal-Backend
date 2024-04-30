package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.RewardRequest;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import com.fyp.MyParentPal.Service.TaskSubmissionServices;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/task_submission")
public class TaskSubmissionController {
    @Autowired
    private TaskSubmissionServices Services;
    @Autowired
    private TaskSubmission mysubmission;
    @GetMapping(value = "/getall")
    public Iterable<TaskSubmission> getSubmitFile() {
        return Services.listAll();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> upload(@ModelAttribute TaskSubmission submission,
                                         @RequestParam("file") MultipartFile file) {
        try {
            // Get file bytes
            byte[] fileBytes = file.getBytes();

            // Get file name
            String fileName = file.getOriginalFilename();

            // Get file type
            String fileType = file.getContentType();

            // Set file details in the TaskSubmission object
            submission.setFileName(fileName);
            submission.setFileType(fileType);
            submission.setFiles(fileBytes);

            // Save the submission
            Services.save(submission);

            return ResponseEntity.ok().body("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error uploading file.");
        }
    }

    @GetMapping(value = "/getSubmission")
    public ResponseEntity<List<TaskSubmission>> getSubmission() {
        try {
            // Check if mySubmission is not null
            if (mysubmission == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            // Fetch all submissions
            String taskId = mysubmission.getTaskid();
            System.out.println("Task id: " + taskId);

            Iterable<TaskSubmission> allTasks = Services.listAll();

            // Filter submission based on the Task ID
            List<TaskSubmission> TaskSubmit = new ArrayList<>();
            for (TaskSubmission submission : allTasks) {
                String submissionTaskId = submission.getTaskid();
                if (submissionTaskId != null && submissionTaskId.equals(taskId)) {
                    TaskSubmit.add(submission);
                }
            }

            return ResponseEntity.ok().body(TaskSubmit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/edit")
    public String sendMessage(@ModelAttribute TaskSubmission tasksubmission,@RequestBody TaskSubmission submission) {


            String message = submission.getMessage();
            // Set file details in the TaskSubmission object
            tasksubmission.setMessage(message);
            System.out.println("Message "+ submission.getMessage());
            // Save the submission
            Services.save(tasksubmission);
            return submission.get_id();

    }

}
