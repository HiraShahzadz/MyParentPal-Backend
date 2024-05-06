package com.fyp.MyParentPal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyTask extends Task{
    //task type tell it is panelty task
    private String taskTypeIs;
    private String taskId;
    private List<String> cheatTags;
}
