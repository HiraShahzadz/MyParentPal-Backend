package com.fyp.MyParentPal.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.util.Arrays;

@Document(collection = "Task_Submission")
@Component
@Data
public class TaskSubmission {
    @Id
    private String _id;
    private String fileName;

    private String taskid;
    private String typedMessage;

    public String getMessage() {
        return typedMessage;
    }

    public void setMessage(String message) {
        this.typedMessage = message;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }


    private String fileType;
   @Field
   private byte [] files;
    public TaskSubmission(String _id, String fileName, String fileType,byte[] files) {
        this._id = _id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.files=files;
    }

    public String get_id() {
        return _id;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] data) {
        this.files = data;
    }


    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public TaskSubmission() {

    }

    @Override
    public String toString() {
        return "TaskSubmission{" +
                "_id='" + _id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", taskid='" + taskid + '\'' +
                ", fileType='" + fileType + '\'' +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}