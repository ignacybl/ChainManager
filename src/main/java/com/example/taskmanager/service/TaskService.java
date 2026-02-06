package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.Status;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.validation.DueDateValidator;
import com.example.taskmanager.validation.TitleValidator;
import com.example.taskmanager.validation.UserExistsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final TitleValidator titleValidator;
    private final DueDateValidator dueDateValidator;
    private final UserExistsValidator userExistsValidator;

    @Transactional
    public TaskResponse createTask(TaskRequest request){
        buildValidationChain();
        titleValidator.validate(request);
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("Użytkownik o ID " + request.userId() + " nie istnieje"));
        Task task = mapToEntity(request, user);
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);

    }
    public List<TaskResponse> getTasksByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Użytkownik o ID " + userId + " nie istnieje");
        }
        return taskRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    private void buildValidationChain(){
        titleValidator.setNext(dueDateValidator);
        dueDateValidator.setNext(userExistsValidator);
    }

    private Task mapToEntity(TaskRequest request, User user){
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status()!=null ? request.status() : Status.TODO);
        task.setPriority(request.priority());
        task.setDueDate(request.dueTime());
        task.setUser(user);
        return task;
    }

    private TaskResponse mapToResponse(Task task){
        String userName = task.getUser() != null ? task.getUser().getName() : "Brak danych";
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus(), userName);

    }

}
