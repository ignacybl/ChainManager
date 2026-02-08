package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.ValidationException;
import com.example.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserExistsValidator extends AbstractTaskValidator{
    private final UserRepository userRepository;
    @Override
    public void validate(TaskRequest request) {
        if(request.userId() == null){
            throw new ValidationException("Brakuje ID użytkownika");
        }
        if(!userRepository.existsById(request.userId())){
            throw new ValidationException("Użytkownik o ID " + request.userId() + " nie istnieje");
        }
        callNext(request);

    }
}
