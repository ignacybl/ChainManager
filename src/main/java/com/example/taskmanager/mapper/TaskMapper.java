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
    @Mapping(source = "dueTime", target = "dueDate")
    Task toEntity(TaskRequest taskRequest);
    @Mapping(target = "userName", expression = "java(task.getUser() != null ? task.getUser().getName() : null)")
    TaskResponse toResponse(Task task);
}