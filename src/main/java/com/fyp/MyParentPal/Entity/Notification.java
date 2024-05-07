package com.fyp.MyParentPal.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;
    private String  childId;
    private String  parentid;
    private String time;
    private String ChildName;
    private String taskname;
    private String taskdescription;
    private String desiredreward;
    private String rewarddescription;
    private String message;

}
