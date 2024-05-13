package com.fyp.MyParentPal.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;
    private String  childId;
    private String  parentid;
    private String time;
    private LocalDate date;
    private LocalTime localtime;
    private String ChildName;
    private String taskname;
    private String taskdescription;
    private String desiredreward;
    private String rewarddescription;
    private String message;

}
