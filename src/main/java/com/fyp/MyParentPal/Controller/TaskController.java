package com.fyp.MyParentPal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/task")
public class TaskController {
	
	@Autowired
    private TaskServices taskServices;

    @PostMapping(value = "/save")
    public String saveTask(@RequestBody Task tasks) {

        taskServices.saveorUpdate(tasks);
        return tasks.get_id();
    }
    
    @GetMapping(value = "/getall")
    public Iterable<Task> getTasks() {
        return taskServices.listAll();
    }
    
   /* @PutMapping(value = "/edit/{id}")
    private Task update(@RequestBody Task tasks, @PathVariable(name = "id") String _id) {
    	tasks.set_id(_id);
    	taskServices.saveorUpdate(tasks);
    	System.out.println(tasks);
        return tasks;
    }
    */
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
}
