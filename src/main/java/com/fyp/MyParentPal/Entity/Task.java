package com.fyp.MyParentPal.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List; 
import java.util.Date;

@Document(collection = "tasks")
public class Task {

	@Id
	private String _id;
	private String taskname;
	private String taskdescription;
	private String status;
	private String rewardname;
	private List<String> taskfiletype;
	@JsonFormat(pattern = "MM/dd/yyyy", timezone = "UTC")
	private Date taskdate;
	private String tasktime;
	private String tasktag;
	private String taskassignee;
	private String tasktype;

	public Task() {
	}

	public Task(String _id, String taskname, String taskdescription, String status, String rewardname,
			List<String> taskfiletype, Date taskdate, String tasktime, String tasktag, String taskassignee,
			String tasktype) {

		this._id = _id;
		this.taskname = taskname;
		this.taskdescription = taskdescription;
		this.status = status;
		this.rewardname = rewardname;
		this.taskfiletype = taskfiletype;
		this.taskdate = taskdate;
		this.tasktime = tasktime;
		this.tasktag = tasktag;
		this.taskassignee = taskassignee;
		this.tasktype = tasktype;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRewardname() {
		return rewardname;
	}

	public void setRewardname(String rewardname) {
		this.rewardname = rewardname;
	}

	public List<String> getTaskfiletype() {
		return taskfiletype;
	}

	public void setTaskfiletype(List<String> taskfiletype) {
		this.taskfiletype = taskfiletype;
	}

	public Date getTaskdate() {
		return taskdate;
	}

	public void setTaskdate(Date taskdate) {
		this.taskdate = taskdate;
	}

	public String getTasktime() {
		return tasktime;
	}

	public void setTasktime(String tasktime) {
		this.tasktime = tasktime;
	}

	public String getTasktag() {
		return tasktag;
	}

	public void setTasktag(String tasktag) {
		this.tasktag = tasktag;
	}

	public String getTaskassignee() {
		return taskassignee;
	}

	public void setTaskassignee(String taskassignee) {
		this.taskassignee = taskassignee;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	@Override
	public String toString() {
		return "Task [_id=" + _id + ", taskname=" + taskname + ", taskdescription=" + taskdescription + ", status="
				+ status + ", rewardname=" + rewardname + ", taskfiletype=" + taskfiletype + ", taskdate=" + taskdate
				+ ", tasktime=" + tasktime + ", tasktag=" + tasktag + ", taskassignee=" + taskassignee + ", tasktype="
				+ tasktype + "]";
	}

}
