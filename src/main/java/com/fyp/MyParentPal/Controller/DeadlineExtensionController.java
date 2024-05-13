package com.fyp.MyParentPal.Controller;

import com.fyp.MyParentPal.Entity.DeadlineExtension;
import com.fyp.MyParentPal.Entity.Task;
import com.fyp.MyParentPal.Entity.TaskSubmission;
import com.fyp.MyParentPal.Service.DeadlineExtensionServices;
import com.fyp.MyParentPal.Service.TaskSubmissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/dateExtension")
public class DeadlineExtensionController {

    @Autowired
    private DeadlineExtensionServices Services;

    @Autowired
    private Task mytask;


    @GetMapping(value = "/getall")
    public Iterable<DeadlineExtension> getMessage() {
        return Services.listAll();
    }

    @PostMapping(value = "/send")
    public ResponseEntity<DeadlineExtension> send(@RequestParam(name = "taskid") String taskId, @RequestBody DeadlineExtension dateExtension) {
        try {
                String childId = mytask.getChildId();
                dateExtension.setChildId(childId);
                String Message = dateExtension.getTypedMessage();
                dateExtension.setTypedMessage(Message);
                dateExtension.setTaskid(taskId);
                Services.save(dateExtension);
            return ResponseEntity.ok().body(dateExtension);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
