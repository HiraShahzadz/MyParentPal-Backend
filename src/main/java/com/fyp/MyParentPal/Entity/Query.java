package com.fyp.MyParentPal.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "queries")
public class Query {
    @Id
    private String id;
    private String name;
    private String email;
    private String query;
    private LocalDate date;
    private String status;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Query() {
        this.status = "pending"; // Set default status to "pending"
    }
    public Query(String id, String name, String email, String query, LocalDate date,String status, String response) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.query = query;
        this.date=date;
        this.status = (status != null && !status.isEmpty()) ? status : "pending";
        this.response=response;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Query{" +
                "_id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", query='" + query + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
