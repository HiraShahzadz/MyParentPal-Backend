package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.Parent;
import com.fyp.MyParentPal.Entity.RewardRequest;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import com.fyp.MyParentPal.Service.TaskServices;
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
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam(name = "taskid") String taskId) {

        try {
            // Check if a submission already exists for the given taskId
            TaskSubmission existingSubmission = Services.getByTaskId(taskId);

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


            if (existingSubmission == null) {
                // Save the submission
                 Services.save(submission);


            } else {
                // Update the existing submission
                existingSubmission.setFileName(fileName);
                existingSubmission.setFileType(fileType);
                existingSubmission.setFiles(fileBytes);
                Services.save(existingSubmission);

            }

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

    @PostMapping(value = "/send")
    public ResponseEntity<TaskSubmission> sendOrEditSubmission(@RequestParam(name = "taskid") String taskId, @RequestBody TaskSubmission submission) {
        try {
            // Check if a submission already exists for the given taskId
            TaskSubmission existingSubmission = Services.getByTaskId(taskId);

            if (existingSubmission == null) {
                // If no submission exists, create a new one
                // Set the taskId for the new submission
                String message = submission.getMessage();
                submission.setTaskid(taskId); // Set the taskid
                submission.setMessage(message);
                Services.save(submission);

                return ResponseEntity.ok(submission);
            } else {
                // If a submission exists, update it with the new message
                String typedMessage = submission.getTypedMessage();
                existingSubmission.setMessage(typedMessage);
                Services.save(existingSubmission);

                return ResponseEntity.ok(existingSubmission);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}

