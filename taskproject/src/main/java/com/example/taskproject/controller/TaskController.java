package com.example.taskproject.controller;

import com.example.taskproject.payload.TaskDTO;
import com.example.taskproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //create task
    @PostMapping("/{userId}/tasks")
    public ResponseEntity<TaskDTO> saveTask(
            @PathVariable(name = "userId") long userId, @RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.saveTask(userId,taskDTO), HttpStatus.CREATED);
    }

    //get all tasks
    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable(name = "userId") long userId)
    {
        return new ResponseEntity<>(taskService.getAllTasks(userId),HttpStatus.OK);
    }

    //get individual task
    @GetMapping("{userId}/tasks/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable(name = "userId") long userId,@PathVariable(name = "taskId") long taskId)
    {

        return new ResponseEntity<>(taskService.getTask(userId,taskId),HttpStatus.OK);

    }

    //del individual task
    @DeleteMapping("/{taskId}/task")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable(name = "taskId") long taskId)
    {
        return new ResponseEntity<>(taskService.deleteTask(taskId),HttpStatus.OK);

    }

}
