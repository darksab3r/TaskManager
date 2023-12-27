package com.project.TaskManager.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Task {
    Integer id;
    String name;
    @Setter
    Date dueDate;
    @Setter
    Boolean completed;
}
