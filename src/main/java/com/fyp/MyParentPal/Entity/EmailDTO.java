package com.fyp.MyParentPal.Entity;

public class EmailDTO {
    private String toEmail;
    private String subject;
    private String body;
    public EmailDTO() {
        this.subject = "Response To Query"; // Set default status to "pending"
    }
    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
