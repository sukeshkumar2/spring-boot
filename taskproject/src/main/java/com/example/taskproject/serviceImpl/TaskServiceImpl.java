package com.example.taskproject.serviceImpl;

import com.example.taskproject.entity.Task;
import com.example.taskproject.entity.Users;
import com.example.taskproject.exception.ApiException;
import com.example.taskproject.exception.TaskNotFound;
import com.example.taskproject.exception.UserNotFound;
import com.example.taskproject.payload.TaskDTO;
import com.example.taskproject.repository.TaskRepository;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TaskRepository taskRepo;
    @Override
    public TaskDTO saveTask(long userId, TaskDTO taskDto) {

        Users user = userRepo.findById(userId).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userId))
        );
        Task task = mapper.map(taskDto,Task.class);
        task.setUsers(user);
        Task savedTask = taskRepo.save(task);

        return mapper.map(savedTask,TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTasks(long userId) {
        Users user = userRepo.findById(userId).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userId))
        );

        List<Task> tasks = taskRepo.findAllByUsersId(userId);
        return tasks.stream().map(task -> mapper.map(task,TaskDTO.class)).collect(Collectors.toList());

    }

    @Override
    public TaskDTO getTask(long userId,long taskId) {
        Users user = userRepo.findById(userId).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userId))
        );
        Task task = taskRepo.findById(taskId).orElseThrow(
                ()-> new TaskNotFound(String.format("Task %d Not Found",taskId))
        );
        if(user.getId() != task.getUsers().getId())
        {
            throw new ApiException(String.format("Task Id %d doesn't belongs to User Id %d ",taskId,userId));
        }

        return mapper.map(task,TaskDTO.class);

    }

    @Override
    public TaskDTO deleteTask(long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(
                ()->new TaskNotFound(String.format("Task %d Not Found",taskId))
        );
       taskRepo.deleteById(taskId);
       return mapper.map(task,TaskDTO.class);
    }
}
