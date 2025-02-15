package com.fyp.MyParentPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    private String id;
    private String name;
    private String description;
    private String parentId;
}
