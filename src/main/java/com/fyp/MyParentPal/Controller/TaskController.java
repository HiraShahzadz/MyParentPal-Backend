package com.fyp.MyParentPal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Service.TaskServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/task")
public class TaskController {
	
	@Autowired
    private TaskServices taskServices;
    @Autowired
    private Task mytask;
    @PostMapping(value = "/save")
    public String saveTask(@RequestBody Task tasks) {

        taskServices.saveorUpdate(tasks);
        return tasks.get_id();
    }
    
    @GetMapping(value = "/getall")
    public Iterable<Task> getTasks() {
        return taskServices.listAll();
    }

    @PutMapping(value = "/edit_task/{id}")
    private Task update(@RequestBody Task tasks, @PathVariable(name = "id") String _id) {
        // Set the _id received from the path variable
        tasks.set_id(_id);

        // Retrieve the original task from the database using the _id
        Task originalTask = taskServices.getTaskByID(_id);

        // Set the id and status values from the original task to prevent updating them
        tasks.set_id(originalTask.get_id());
        tasks.setStatus(originalTask.getStatus());
        tasks.setTaskassignee(originalTask.getTaskassignee());
        tasks.setTasktype(originalTask.getTasktype());
        tasks.setChildId(originalTask.getChildId());
        // Save or update the task
        taskServices.saveorUpdate(tasks);

        // Print the updated task for debugging
        System.out.println(tasks);

        // Return the updated task
        return tasks;
    }


    @PutMapping(value = "/edit/{id}")
    private Task updateStatus(@RequestBody Task updatedTask, @PathVariable(name = "id") String _id) {
        Task existingTask = taskServices.getTaskByID(_id);
        
        if (existingTask != null) {
            existingTask.setStatus(updatedTask.getStatus());
            taskServices.saveorUpdate(existingTask);
            System.out.println(existingTask);
            return existingTask;
        } else {
            // Handle the case when the task with the given ID is not found
            // You might want to return an appropriate response or throw an exception
            return null;
        }
    }

    
    @DeleteMapping("/delete/{id}")
    private void deleteTask(@PathVariable("id") String _id) {
    	taskServices.deleteTask(_id);
    }


    @RequestMapping("/search/{id}")
    private Task getTask(@PathVariable(name = "id") String taskid) {
        return taskServices.getTaskByID(taskid);
    }


    @GetMapping(value = "/getTasks")
    public ResponseEntity<List<Task>> getChildTasks() {
        try {
            // Check if mytask is not null
            if (mytask == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // Fetch all tasks
            String childId = mytask.getChildId();
            System.out.println("Child id: " + childId);

            Iterable<Task> allTasks = taskServices.listAll();

            // Filter tasks based on the child ID
            List<Task> childTasks = new ArrayList<>();
            for (Task task : allTasks) {
                String taskChildId = task.getChildId();
                if (taskChildId != null && taskChildId.equals(childId)) {
                    childTasks.add(task);
                }
            }

            return ResponseEntity.ok().body(childTasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}

