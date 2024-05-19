package com.fyp.MyParentPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String id;
    private String email;
    private String password;
    private String name;
    private String dob;
    private String status;
    private byte[] image;
    private LocalTime localtime;
    private String img;
    private String childId;
}
