package com.fyp.MyParentPal.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List; 
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
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
	private String childId;

	public String get_id() {
		return _id;
	}

	public String getTaskname() {
		return taskname;
	}

	public String getTaskdescription() {
		return taskdescription;
	}

	public String getStatus() {
		return status;
	}

	public String getRewardname() {
		return rewardname;
	}

	public List<String> getTaskfiletype() {
		return taskfiletype;
	}

	public Date getTaskdate() {
		return taskdate;
	}

	public String getTasktime() {
		return tasktime;
	}

	public String getTasktag() {
		return tasktag;
	}

	public String getTaskassignee() {
		return taskassignee;
	}

	public String getTasktype() {
		return tasktype;
	}

	public String getChildId() {
		return childId;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public void setTaskdescription(String taskdescription) {
		this.taskdescription = taskdescription;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRewardname(String rewardname) {
		this.rewardname = rewardname;
	}

	public void setTaskfiletype(List<String> taskfiletype) {
		this.taskfiletype = taskfiletype;
	}

	public void setTaskdate(Date taskdate) {
		this.taskdate = taskdate;
	}

	public void setTasktime(String tasktime) {
		this.tasktime = tasktime;
	}

	public void setTasktag(String tasktag) {
		this.tasktag = tasktag;
	}

	public void setTaskassignee(String taskassignee) {
		this.taskassignee = taskassignee;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	@Override
	public String toString() {
		return "Task{" +
				"_id='" + _id + '\'' +
				", taskname='" + taskname + '\'' +
				", taskdescription='" + taskdescription + '\'' +
				", status='" + status + '\'' +
				", rewardname='" + rewardname + '\'' +
				", taskfiletype=" + taskfiletype +
				", taskdate=" + taskdate +
				", tasktime='" + tasktime + '\'' +
				", tasktag='" + tasktag + '\'' +
				", taskassignee='" + taskassignee + '\'' +
				", tasktype='" + tasktype + '\'' +
				", childId='" + childId + '\'' +
				'}';
	}
}
