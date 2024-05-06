package com.fyp.MyParentPal.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.ZoneId;
@Document(collection = "Reward_Request")
public class RewardRequest {

    @Id
    private String _id;
    private String taskname;
    private String taskdescription;
    private String desiredreward;
    private String rewarddescription;
    @JsonFormat(pattern = "MM/dd/yyyy", timezone = "UTC")
    private LocalDate date;
    private String status;
    private String childId;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public RewardRequest() {
    }

    public RewardRequest(String _id, String taskname, String taskdescription, String desiredreward,
                         String rewarddescription, LocalDate date, String status) {
        super();
        this._id = _id;
        this.taskname = taskname;
        this.taskdescription = taskdescription;
        this.desiredreward = desiredreward;
        this.rewarddescription = rewarddescription;
        this.date= date;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskdescription() {
        return taskdescription;
    }

    public void setTaskdescription(String taskdescription) {
        this.taskdescription = taskdescription;
    }

    public String getDesiredreward() {
        return desiredreward;
    }

    public void setDesiredreward(String desiredreward) {
        this.desiredreward = desiredreward;
    }

    public String getRewarddescription() {
        return rewarddescription;
    }

    public void setRewarddescription(String rewarddescription) {
        this.rewarddescription = rewarddescription;
    }

    @Override
    public String toString() {
        return "RewardRequest[" +
                "_id='" + _id + '\'' +
                ", taskname='" + taskname + '\'' +
                ", taskdescription='" + taskdescription + '\'' +
                ", desiredreward='" + desiredreward + '\'' +
                ", rewarddescription='" + rewarddescription + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ']';
    }
}