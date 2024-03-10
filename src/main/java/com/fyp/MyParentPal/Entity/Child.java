package com.fyp.MyParentPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Child extends User {

    private String dob;
    private String gender;
    private List<String> tags;
    private byte[] image;
    private String img; // Base64 encoded image string
    private String parentId;
}

