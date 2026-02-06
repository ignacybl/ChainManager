package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponse> getUserTasks (@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }
}