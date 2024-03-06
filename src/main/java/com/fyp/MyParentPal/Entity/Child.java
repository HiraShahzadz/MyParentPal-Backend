package com.fyp.MyParentPal.Entity;

import java.util.List;


public class Child extends User {

    private String dob;
    private String gender;
    private List<String> tags;
    private byte[] image;
    private String img; // Base64 encoded image string


// constructors, getters, setters, and other methods


    public Child() {

    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

