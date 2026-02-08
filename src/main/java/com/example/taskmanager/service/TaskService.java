package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.Status;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.validation.DueDateValidator;
import com.example.taskmanager.validation.TaskValidator;
import com.example.taskmanager.validation.TitleValidator;
import com.example.taskmanager.validation.UserExistsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskValidator validationChain;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(TaskRequest request){
        validationChain.validate(request);
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new EntityNotFoundException("Użytkownik o ID " + request.userId() + " nie istnieje"));
        Task task = taskMapper.toEntity(request);
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    public List<TaskResponse> getTasksByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Użytkownik o ID " + userId + " nie istnieje");
        }
        return taskRepository.findByUserId(userId).stream()
                .map(taskMapper::toResponse)
                .toList();
    }
    
}
