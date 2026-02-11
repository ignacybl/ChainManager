package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequest;

public abstract class AbstractTaskValidator implements TaskValidator{
    protected TaskValidator next;

    @Override
    public TaskValidator setNext(TaskValidator next){
        this.next = next;
        return next;
    }
    protected void callNext(TaskRequest request){
        if(next!=null){
            next.validate(request);
        }
    }
}
