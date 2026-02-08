package com.example.taskmanager.config;

import com.example.taskmanager.validation.DueDateValidator;
import com.example.taskmanager.validation.TaskValidator;
import com.example.taskmanager.validation.TitleValidator;
import com.example.taskmanager.validation.UserExistsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationChainConfig {
    @Bean
    public TaskValidator validationChain(TitleValidator titleValidator, DueDateValidator dueDateValidator, UserExistsValidator userExistsValidator){
        titleValidator.setNext(dueDateValidator).setNext(userExistsValidator);
        return titleValidator;
    }
}
