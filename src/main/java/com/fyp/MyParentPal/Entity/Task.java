package com.fyp.MyParentPal.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List; 
import java.util.Date;
import lombok.Data;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
