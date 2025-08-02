package com.example.tasker.webapp.api.controllers;

import com.example.tasker.models.model.TaskPage;
import com.example.tasker.skeleton.api.TasksApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-tasker")
public class TaskController implements TasksApi {
    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @Override
    public ResponseEntity<TaskPage> getTasks(Integer size, Integer page){
        return new ResponseEntity<>(new TaskPage(), HttpStatus.OK);
    }
}
