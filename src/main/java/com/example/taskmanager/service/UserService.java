package com.example.taskmanager.service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Page<User> getAllUsers(){
        return userRepository.findAll(PageRequest.of(1,2));
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Użytkownik nie istnieje"));
    }
}
