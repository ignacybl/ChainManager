package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.ValidationException;
import com.example.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserExistsValidator implements TaskValidator{
    private final UserRepository userRepository;
    private TaskValidator next;


    @Override
    public void validate(TaskRequest request) {
        if(request.userId() == null){
            throw new ValidationException("Brakuje ID użytkownika");
        }
        if(!userRepository.existsById(request.userId())){
            throw new ValidationException("Użytkownik o ID " + request.userId() + " nie istnieje");
        }
        if(this.next != null){
            this.next.validate(request);
        }

    }

    @Override
    public void setNext(TaskValidator next) {
        this.next = next;
    }
}
