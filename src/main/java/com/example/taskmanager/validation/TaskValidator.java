package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequest;

import javax.xml.validation.Validator;

public interface TaskValidator {
    void validate(TaskRequest request);
    TaskValidator setNext(TaskValidator next);
}
