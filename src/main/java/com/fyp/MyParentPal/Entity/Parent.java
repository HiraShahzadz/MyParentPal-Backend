package com.fyp.MyParentPal.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="parents")
public class Parent {

    @Id
    private String _id;

    private String email;
    private String password;


    public Parent(String _id, String email, String password) {
        this._id=_id;
        this.email = email;
        this.password= password;

    }
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
    public Parent() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id='" + _id + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
