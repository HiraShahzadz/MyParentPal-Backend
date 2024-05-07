package com.fyp.MyParentPal.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeadlineExtension {
    @Id
    private String id;
    private String taskid;
    private String childId;
    private String typedMessage;
}
