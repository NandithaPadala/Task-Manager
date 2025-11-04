package com.demo.taskproject.service;

import com.demo.taskproject.entity.Task;
import com.demo.taskproject.payload.TaskDto;

import java.util.List;

public interface TaskService {

    public TaskDto saveTask(long userId, TaskDto taskDto);

    public List<TaskDto> getAllTasks(long userId);

    public TaskDto getTask(long userId, long taskId);

    public void deleteTask(long userId, long taskId);

    public void updateTask(long taskId, TaskDto taskDto);

    public List<Task> getAllTakss();
}
