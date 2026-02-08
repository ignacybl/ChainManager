package com.example.taskmanager.service;

import com.example.taskmanager.dto.UserRequest;
import com.example.taskmanager.dto.UserResponse;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.mapper.UserMapper;
import com.example.taskmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse createUser(UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public Page<UserResponse> getAllUsers(){
        Page<User> users = userRepository.findAll(PageRequest.of(0,2));
        return users.map(userMapper::toResponse);
    }
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Użytkownik nie istnieje"));
        return userMapper.toResponse(user);
    }

}
