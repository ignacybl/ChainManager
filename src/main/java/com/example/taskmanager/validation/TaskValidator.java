package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequest;

public interface TaskValidator {
    void validate(TaskRequest request);
    void setNext(TaskValidator next);
}
