package com.project.TaskManager.tasks.dtos;
import lombok.Data;

import java.util.Date;
@Data
public class UpdateTaskDTO {
    Date dueDate;
    Boolean completed;
}
