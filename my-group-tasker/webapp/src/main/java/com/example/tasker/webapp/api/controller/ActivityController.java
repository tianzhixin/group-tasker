package com.example.tasker.webapp.api.controller;

import com.example.tasker.models.model.Group;
import com.example.tasker.skeleton.api.ActivitiesApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-tasker")
public class ActivityController implements ActivitiesApi {
    private static final Logger LOG = LoggerFactory.getLogger(GroupController.class);

    public ResponseEntity<Group> getActivities(Integer size, Integer page){
        return new ResponseEntity<>(new Group(), HttpStatus.OK);
    }

}