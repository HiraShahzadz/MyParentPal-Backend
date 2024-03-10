package com.fyp.MyParentPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent extends User {

    private String cnic;
    private List<String> children;
}
