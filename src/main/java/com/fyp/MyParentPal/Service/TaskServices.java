package com.fyp.MyParentPal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Repo.TaskRepo;


@Service
public class TaskServices {

    @Autowired
    private TaskRepo repo;

    public void saveorUpdate(Task tasks) {
        repo.save(tasks);
    }

    public Iterable<Task> listAll() {

        return this.repo.findAll();
    }


    public void deleteTask(String id) {

        repo.deleteById(id);
    }

    public Task getTaskByID(String taskid) {

        return repo.findById(taskid).get();
    }
}
