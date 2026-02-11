package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotNull;

public record UserRequest(@NotNull(message = "name cannot be null") String name,@NotNull(message = "email cannot be null") String email) {
}
