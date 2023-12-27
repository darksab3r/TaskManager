package com.project.TaskManager.tasks.dtos;

import lombok.Data; //automatically ads getters and setters

import java.util.Date;

@Data
public class CreateTaskDTO {
    String name;
    Date dueDate;
}
