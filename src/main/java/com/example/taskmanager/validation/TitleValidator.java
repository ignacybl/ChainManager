package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class TitleValidator extends AbstractTaskValidator{

    @Override
    public void validate(TaskRequest request) {
        if(request.title() == null || request.title().isBlank())
            throw new ValidationException("Tytuł zadania nie może być pusty");

        callNext(request);
    }
}
