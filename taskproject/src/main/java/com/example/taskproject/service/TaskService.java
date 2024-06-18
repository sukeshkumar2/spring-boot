package com.example.taskproject.service;

import com.example.taskproject.payload.TaskDTO;

import java.util.List;

public interface TaskService {

    public TaskDTO saveTask(long userId, TaskDTO taskDto);

    public List<TaskDTO> getAllTasks(long userId);

    public TaskDTO getTask(long userId,long taskId);

    public TaskDTO deleteTask(long taskId);

}
