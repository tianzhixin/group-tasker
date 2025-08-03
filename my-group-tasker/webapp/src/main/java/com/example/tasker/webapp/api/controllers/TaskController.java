package com.example.tasker.webapp.api.controllers;

import com.example.tasker.models.model.TaskPage;
import com.example.tasker.skeleton.api.TasksApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-tasker")
@Slf4j
public class TaskController implements TasksApi {
    @Override
    public ResponseEntity<TaskPage> getTasks(Integer size, Integer page){
        return new ResponseEntity<>(new TaskPage(), HttpStatus.OK);
    }
}
