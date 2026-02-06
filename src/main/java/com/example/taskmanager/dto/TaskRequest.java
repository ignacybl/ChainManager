package com.example.taskmanager.dto;

import com.example.taskmanager.enums.Priority;
import com.example.taskmanager.enums.Status;

import java.time.LocalDateTime;

public record TaskRequest (String title, String description, Status status, Priority priority, LocalDateTime dueTime, Long userId) {
}
