package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DueDateValidator implements TaskValidator {
    private TaskValidator next;

    @Override
    public void validate(TaskRequest request) {
        if(request.dueTime() != null && request.dueTime().isBefore(LocalDateTime.now())){
            throw new ValidationException("Data zakończenia nie może być w przeszłości");
        }
        if(this.next!= null){
            this.next.validate(request);
        }

    }

    @Override
    public void setNext(TaskValidator next) {
        this.next = next;
    }
}
