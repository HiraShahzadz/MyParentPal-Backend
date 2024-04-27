package com.fyp.MyParentPal.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.sql.Blob;
import java.util.Arrays;

@Document(collection = "Task_Submission")
public class TaskSubmission {
    @Id
    private String _id;
    private String fileName;
    private String typedMessage;

    public String gettypedMessage() {
        return typedMessage;
    }

    public void setTextmssage(String typedMessage) {
        this.typedMessage = typedMessage;
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
                ", fileType='" + fileType + '\'' +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}