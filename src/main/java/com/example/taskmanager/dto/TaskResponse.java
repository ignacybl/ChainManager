package com.example.taskmanager.dto;

import com.example.taskmanager.enums.Status;

public record TaskResponse(Long id, String title, Status status, String userName) {
}
