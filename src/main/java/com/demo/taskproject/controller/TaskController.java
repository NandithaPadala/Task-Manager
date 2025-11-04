package com.demo.taskproject.controller;

import com.demo.taskproject.entity.Task;
import com.demo.taskproject.payload.TaskDto;
import com.demo.taskproject.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //save the task
    @PostMapping("/{userid}/tasks")
    public ResponseEntity<TaskDto> saveTask(
            @PathVariable(name = "userid") long id,
            @RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.saveTask(id, taskDto), HttpStatus.CREATED);
    }


    //get all task
//    @PreAuthorize(value = "USER")
    @GetMapping("/{userid}/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable(name = "userid") long userId) {
        return new ResponseEntity<>(taskService.getAllTasks(userId), HttpStatus.OK);
    }


    //get individual task
    @GetMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<TaskDto> getTask(
            @PathVariable(name = "userid") long userId,
            @PathVariable(name = "taskid") long taskId) {
        return new ResponseEntity<>(taskService.getTask(userId, taskId), HttpStatus.OK);
    }


    //delete individual task
    @DeleteMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<String> deleteTask(
            @PathVariable(name = "userid") long userId,
            @PathVariable(name = "taskid") long taskId) {

        taskService.deleteTask(userId, taskId);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/tasks/{taskid}")
    public ResponseEntity<String> updateTask(
            @PathVariable(name = "taskid") long taskId,
            @RequestBody TaskDto taskDto) {

        taskService.updateTask(taskId, taskDto);
        return new ResponseEntity<>("User with taskId: " + taskId + " is updated", HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public List<Task> getAllTaks() {
        return taskService.getAllTakss();
    }


}
