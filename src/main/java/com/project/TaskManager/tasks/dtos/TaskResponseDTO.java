package com.project.TaskManager.tasks.dtos;

import com.project.TaskManager.tasks.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class TaskResponseDTO {
    Integer id;
    String name;
    Date dueDate;
    Boolean completed;

    public TaskResponseDTO(Task task){
        this.id=task.getId();
        this.name= task.getName();
        this.dueDate=task.getDueDate();
        this.completed=task.getCompleted();
    }
}
