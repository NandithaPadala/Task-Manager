package com.demo.taskproject.serviceImplementation;

import com.demo.taskproject.entity.Task;
import com.demo.taskproject.entity.Users;
import com.demo.taskproject.exception.APIException;
import com.demo.taskproject.exception.TaskNotFoundException;
import com.demo.taskproject.exception.UserNotFoundException;
import com.demo.taskproject.payload.TaskDto;
import com.demo.taskproject.repository.TaskRepository;
import com.demo.taskproject.repository.UserRepository;
import com.demo.taskproject.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDto saveTask(long userId, TaskDto taskDto) {

        Users user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User id %d not found", userId))
        );
        Task task = DtoToEntity(taskDto);
        task.setUser(user);

        // After setting the user into the task
        Task savedTask = taskRepository.save(task);

        return EntityToDto(savedTask);
    }

    @Override
    public List<TaskDto> getAllTasks(long userId) {

        Users user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User id %d not found", userId))
        );
        List<Task> tasks = taskRepository.findAllByUserId(userId);

        return tasks.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    private TaskDto EntityToDto(Task savedTask) {

        TaskDto taskDto = new TaskDto();
        taskDto.setId(savedTask.getId());
        taskDto.setTaskname(savedTask.getTaskname());

        return taskDto;
    }

    private Task DtoToEntity(TaskDto taskDto) {

        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTaskname(taskDto.getTaskname());

        return task;
    }

    public TaskDto getTask(long userId, long taskId) {
        Users user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User id %d not found", userId))
        );
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException(String.format("Task id %d not found", taskId))
        );

        if (user.getId() != task.getUser().getId()) {
            throw new APIException(String.format("Task Id %d is not belongs to User Id %d ", taskId, userId));
        }

        return EntityToDto(task);
    }

    public void deleteTask(long userId, long taskId) {

        Users user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User id %d not found", userId))
        );
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException(String.format("Task id %d not found", taskId))
        );

        if (user.getId() != task.getUser().getId()) {
            throw new APIException(String.format("Task Id %d is not belongs to User Id %d ", taskId, userId));
        }

        taskRepository.deleteById(taskId);

    }

    @Override
    public void updateTask(long taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException(String.format("Task id %d not found", taskId))
        );

        taskRepository.updateTaskName(taskDto.getTaskname(), taskId);
    }

    @Override
    public List<Task> getAllTakss() {
        return taskRepository.findAll();
    };


}
