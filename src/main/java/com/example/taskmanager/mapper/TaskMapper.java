package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", defaultValue = "TODO")
    Task toEntity(TaskRequest taskRequest);
    @Mapping(source = "user.name", target = "userName")
    TaskResponse toResponse(Task task);
}
