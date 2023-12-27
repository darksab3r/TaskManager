package com.project.TaskManager.tasks;

import com.project.TaskManager.tasks.dtos.CreateTaskDTO;
import com.project.TaskManager.tasks.dtos.TaskResponseDTO;
import com.project.TaskManager.tasks.dtos.UpdateTaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.project.TaskManager.tasks.errorResponse.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks/")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("")
    ResponseEntity<List <TaskResponseDTO>> getAllTasks(){
        var tasks = tasksService.getAllTasks();
        List<TaskResponseDTO> responseDTOList = new ArrayList<>();
        for(var task:tasks){
            responseDTOList.add(new TaskResponseDTO(task));
        }
        return ResponseEntity.ok(responseDTOList);
    }
    @GetMapping("/{id}")
    ResponseEntity <TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
         var task = tasksService.getTaskById(id);
         return ResponseEntity.ok(new TaskResponseDTO(task));
    }
    @PostMapping("")
    ResponseEntity<TaskResponseDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO){
        var task = tasksService.createTask(createTaskDTO.getName(),createTaskDTO.getDueDate());
        return ResponseEntity.ok(new TaskResponseDTO(task));
    }
    @PatchMapping("/{id}")
    ResponseEntity<TaskResponseDTO> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO updateTaskDTO){
        var task = tasksService.updateTask(id,updateTaskDTO.getDueDate(),updateTaskDTO.getCompleted());
        return ResponseEntity.ok(new TaskResponseDTO(task));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable("id")Integer id){
        tasksService.deleteTask(id);
        return ResponseEntity.ok().build(); // build return response entity without a body.
    }

}
