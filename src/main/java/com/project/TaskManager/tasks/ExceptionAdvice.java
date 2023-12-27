package com.project.TaskManager.tasks;

import com.project.TaskManager.tasks.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    /*
    This will handle written below exceptions from all the controller
     */
    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleTaskNotFoundException(TasksService.TaskNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler({
            TasksService.InvalidDueDateException.class,
            TasksService.InvalidNameException.class,
            DateTimeParseException.class  // Handle DateTimeParseException for invalid date format
    })
    ResponseEntity<ErrorResponse> handleInvalidInputException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
