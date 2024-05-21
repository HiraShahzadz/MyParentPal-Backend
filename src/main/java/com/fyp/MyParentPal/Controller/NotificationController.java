package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.*;
import com.fyp.MyParentPal.Repo.NotificationRepo;
import com.fyp.MyParentPal.Service.NotificationServices;
import com.fyp.MyParentPal.Service.TaskServices;
import com.fyp.MyParentPal.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/notify")
public class NotificationController {

    @Autowired
    private NotificationServices messageService;
    @Autowired
    private UserServices userServices;
    // Fetch All User Data
    @Autowired
    TaskServices taskService;
    @Autowired
    private Task mytask;
    @Autowired
    Child mychild;
    @PostMapping("/sendRequestNotification")
    public ResponseEntity<?> sendMessage(@RequestBody Notification notify) {

        String reward = notify.getDesiredreward();
        String ChildId=mytask.getChildId();

        // Assuming you have a method in the user service to fetch the user by ID
        User child = userServices.getUserByID(ChildId);

        // Assuming the User entity has a method to get the child's name
        String childName = child.getName();
        System.out.println("Reward: "+ reward);
        String message = childName + " requested for a reward " + reward ;

        Notification notification = new Notification();

        notification.setMessage(message);
        notification.setChildId(ChildId);
        notification.setTaskname(notify.getTaskname());
        notification.setTaskdescription(notify.getTaskdescription());
        notification.setDesiredreward(notify.getDesiredreward());
        notification.setRewarddescription(notify.getRewarddescription());
        notification.setChildName(childName);

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a 'at' M/d/yyyy");
        String formattedDateTime = dateTime.format(formatter);
        notification.setTime(formattedDateTime);
        messageService.save(notification);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getall")
    public Iterable<Notification> getTasks() {
        return messageService.listAll();
    }

    @PostMapping("/profileNotification")
    public ResponseEntity<Notification> profileNotification() {

        Notification notify = new Notification();
        String ChildId=mytask.getChildId();
        // Assuming you have a method in the user service to fetch the user by ID
        User child = userServices.getUserByID(ChildId);

        // Assuming the User entity has a method to get the child's name
        String childName = child.getName();
        String message = childName + " sent request for profile edit";

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setChildId(ChildId);
        notification.setTaskname(notify.getTaskname());
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a 'at' M/d/yyyy");
        String formattedDateTime = dateTime.format(formatter);
        notification.setTime(formattedDateTime);
        messageService.save(notification);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/messageNotify")
    public ResponseEntity<?> MessageNotification(@RequestParam(name = "taskid") String taskId) {

        Task task = taskService.getTaskByID(taskId);
        String taskName= task.getTaskname();

        Notification notify = new Notification();
        String ChildId=mytask.getChildId();

        // Assuming you have a method in the user service to fetch the user by ID
        User child = userServices.getUserByID(ChildId);

        // Assuming the User entity has a method to get the child's name
        String childName = child.getName();
        String message = childName + " completed the task";

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setChildId(ChildId);
        notification.setTaskname(taskName);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a 'at' M/d/yyyy");
        String formattedDateTime = dateTime.format(formatter);
        notification.setTime(formattedDateTime);
        messageService.save(notification);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/dateExtensionNotify")
    public ResponseEntity<?> ExtensionNotify(@RequestParam(name = "taskid") String taskId) {

        Task task = taskService.getTaskByID(taskId);
        String taskName= task.getTaskname();

        Notification notify = new Notification();
        String ChildId=mytask.getChildId();

        // Assuming you have a method in the user service to fetch the user by ID
        User child = userServices.getUserByID(ChildId);

        // Assuming the User entity has a method to get the child's name
        String childName = child.getName();
        String message = childName + " requested for date extension of task "+ taskName;

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setChildId(ChildId);
        notification.setTaskname(taskName);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a 'at' M/d/yyyy");
        String formattedDateTime = dateTime.format(formatter);
        notification.setTime(formattedDateTime);
        messageService.save(notification);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/assigntaskNotification")
    public ResponseEntity<?> Notification(@RequestBody Task task) {

        Notification notify = new Notification();
        String parentId = mychild.getParentId();
        String childId = task.getChildId();
        String message = "Your parent assigns you a task of "+ task.getTaskname();

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setParentid(parentId);
        notification.setTaskname(task.getTaskname());
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate  date=LocalDate.now();
        LocalTime localtime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy 'at' h:mm a");
        String formattedDateTime = dateTime.format(formatter);
        notification.setTime(formattedDateTime);
        notification.setDate(date);
        notification.setLocaltime(localtime);
        notification.setChildId(childId);
        messageService.save(notification);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/TaskRewardedNotification")
    public ResponseEntity<?> RewardNotification(@RequestBody Task task) {

        Notification notify = new Notification();
        String childId=mytask.getChildId();
        String parentId = mychild.getParentId();

        String message = "Your parent rewarded a task";
        LocalDate  date=LocalDate.now();
        LocalTime localtime = LocalTime.now();
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setChildId(childId);
        notification.setParentid(parentId);
        notification.setDate(date);
        notification.setLocaltime(localtime);
        notification.setTaskname(task.getTaskname());
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a 'at' M/d/yyyy");
        String formattedDateTime = dateTime.format(formatter);
        notification.setTime(formattedDateTime);
        messageService.save(notification);

        return ResponseEntity.ok().build();
    }

}