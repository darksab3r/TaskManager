package com.project.TaskManager.tasks;

import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TasksService { //actual business logic

    private ArrayList <Task> tasks = new ArrayList<>();
    private Integer id = 0;

    public List<Task> getAllTasks(){
        return tasks;
    }
    public Task getTaskById(Integer id){
        for(Task task:tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        throw new TaskNotFoundException(id);
    }

    public Task createTask(String name, Date dueDate){
        if (dueDate != null && dueDate.before(new Date())) {
            throw new InvalidDueDateException("Due date must be today or in the future");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be null, empty, or contain only whitespace");
        }

        // Strip leading and trailing spaces and check if the resulting name length is within the specified range
        String trimmedName = name.trim();
        if (trimmedName.length() < 5 || trimmedName.length() > 100) {
            throw new InvalidNameException("Name must be between 5 and 100 characters");
        }

        Task task = new Task(id++,name,dueDate,false);
        tasks.add(task);
        return task;
    }

    public Task updateTask(Integer id,Date dueDate,Boolean completed){
        Task task = getTaskById(id);
        if(dueDate!=null){
            if (dueDate.before(new Date())) {
                throw new InvalidDueDateException("Due date must be today or in the future");
            }
            else {
                task.setDueDate(dueDate);
            }
        }
        if(completed!=null){
            task.setCompleted(completed);
        }
        return task;
    }

    public void deleteTask(Integer id){
        Task task = getTaskById(id);
        tasks.remove(task);
    }

    public static class TaskNotFoundException extends IllegalStateException{
        public TaskNotFoundException(Integer id){
            super("Task with id "+id+" not found");
        }
    }

    public static class InvalidDueDateException extends DateTimeException{

        public InvalidDueDateException(String message) {
            super(message);
        }
    }

    public class InvalidNameException extends RuntimeException {
        public InvalidNameException(String message) {
            super(message);
        }
    }

}
